package com.news.api.service;

import com.news.api.model.NewsApiResponse;
import com.news.api.model.NewsArticle;
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

    @Value("${gnews.api.key}")
    private String gnewsApiKey;

    private static final String GNEWS_API_URL = "https://gnews.io/api/v4/search";

    private final RestTemplate restTemplate;

    @Autowired
    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("newsArticles")
    public List<NewsArticle> findNewsArticles(int count) {
        String apiUrl = GNEWS_API_URL + "&apikey=" + gnewsApiKey;
        NewsApiResponse response = restTemplate.getForObject(apiUrl, NewsApiResponse.class);
        return Objects.requireNonNull(response)
            .getArticles()
            .stream()
            .limit(count)
            .toList();
    }

    @Cacheable("newsArticles")
    public List<NewsArticle> findNewsArticles(String query, String title, String author, int count) {
        StringBuilder apiUrlBuilder = new StringBuilder(GNEWS_API_URL)
            .append("?apikey=").append(gnewsApiKey)
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

        return Arrays.asList(response != null ? response.getArticles().toArray(new NewsArticle[0]) : new NewsArticle[0]);
    }
}
