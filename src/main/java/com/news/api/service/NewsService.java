package com.news.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.api.model.NewsApiResponse;
import com.news.api.model.NewsArticle;
import net.rubyeye.xmemcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Value("${gnews.api.url}")
    private String gnewsApiUrl;

    @Value("${gnews.api.key}")
    private String gnewsApiKey;

    private final RestTemplate restTemplate;

    private final MemcachedClient memcachedClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public NewsService(RestTemplate restTemplate, MemcachedClient memcachedClient) {
        this.restTemplate = restTemplate;
        this.memcachedClient = memcachedClient;
    }

    public List<NewsArticle> findNewsArticles(String query, String title, String author, int count) {
        try {
            String cacheKey = this.buildCacheKey(query, title, author, count);

            if (memcachedClient.get(cacheKey) != null) {
                logger.info("Cache hit for key: {}", cacheKey);
                String jsonData = memcachedClient.get(cacheKey);
                return objectMapper.readValue(jsonData, new TypeReference<>() {});
            } else {
                logger.info("Cache miss for key: {}", cacheKey);

                NewsSearchApiParamsBuilder apiUrlBuilder = NewsSearchApiParamsBuilder.builder()
                    .gnewsApiUrl(gnewsApiUrl)
                    .gnewsApiKey(gnewsApiKey)
                    .query(query)
                    .title(title)
                    .author(author)
                    .count(count)
                    .build();

                NewsApiResponse response = restTemplate.getForObject(apiUrlBuilder.build(), NewsApiResponse.class);

                List<NewsArticle> newsArticles = Arrays.asList(response != null ? response.getArticles()
                    .toArray(new NewsArticle[0]) : new NewsArticle[0]);

                String cache = objectMapper.writeValueAsString(newsArticles);

                memcachedClient.set(cacheKey, 300, cache);

                return newsArticles;
            }
        } catch (Exception e) {
            logger.error("Error fetching news articles", e);
            return List.of();
        }
    }

    private String buildCacheKey(String query, String title, String author, int count) {
        String sanitizedTitle = (title != null) ? title.replaceAll("[^a-zA-Z0-9]", "") : "null";
        String sanitizedAuthor = (author != null) ? author.replaceAll("[^a-zA-Z0-9]", "") : "null";
        return String.format("%s-%s-%s-%d", query, sanitizedTitle, sanitizedAuthor, count);
    }
}
