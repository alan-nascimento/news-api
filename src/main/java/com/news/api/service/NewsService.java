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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class NewsService {

    private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Value("${gnews.api.url}")
    private String GNEWS_API_URL;

    @Value("${gnews.api.key}")
    private String GNEWS_API_KEY;

    private final RestTemplate restTemplate;

    private final MemcachedClient memcachedClient;

    @Autowired
    public NewsService(RestTemplate restTemplate, MemcachedClient memcachedClient) {
        this.restTemplate = restTemplate;
        this.memcachedClient = memcachedClient;
    }

    @Cacheable("newsArticles")
    public List<NewsArticle> findNewsArticles(int count) {
        String apiUrl = GNEWS_API_URL + "&apikey=" + GNEWS_API_KEY;
        NewsApiResponse response = restTemplate.getForObject(apiUrl, NewsApiResponse.class);
        return Objects.requireNonNull(response)
            .getArticles()
            .stream()
            .limit(count)
            .toList();
    }

    @Cacheable("newsArticles")
    public List<NewsArticle> findNewsArticles(String query, String title, String author, int count) {
        try {
            String cacheKey = query;

            if (memcachedClient.get(cacheKey) != null) {
                logger.info("Cache hit for key: {}", cacheKey);

                String jsonData = memcachedClient.get(cacheKey);

                ObjectMapper objectMapper = new ObjectMapper();
                List<NewsArticle> newsArticles = objectMapper.readValue(jsonData, new TypeReference<List<NewsArticle>>() {});

                return newsArticles;
            } else {
                logger.info("Cache miss for key: {}", cacheKey);

                StringBuilder apiUrlBuilder = new StringBuilder(GNEWS_API_URL)
                    .append("?apikey=").append(GNEWS_API_KEY)
                    .append("&max=").append(count);

                if (query != null && !query.isEmpty()) {
                    apiUrlBuilder.append("&q=").append(query);
                }
                if (title != null && !title.isEmpty()) {
                    apiUrlBuilder.append("&title=").append(title);
                }
                if (author != null && !author.isEmpty()) {
                    apiUrlBuilder.append("&author=").append(author);
                }

                NewsApiResponse response = restTemplate.getForObject(apiUrlBuilder.toString(), NewsApiResponse.class);

                List<NewsArticle> newsArticles = Arrays.asList(response != null ? response.getArticles()
                    .toArray(new NewsArticle[0]) : new NewsArticle[0]);

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(newsArticles);

                memcachedClient.set(cacheKey, 60, json);

                return newsArticles;
            }
        } catch (Exception e) {
            logger.error("Error fetching news articles", e);
            return List.of();
        }
    }
}
