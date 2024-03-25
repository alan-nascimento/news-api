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
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Value("${gnews.api.key}")
    private String gnewsApiKey;

    private static final String GNEWS_API_URL = "https://gnews.io/api/v4/search?q=example&lang=en&country=us&max=10";

    private final RestTemplate restTemplate;

    @Autowired
    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("newsArticles")
    public List<NewsArticle> fetchNewsArticles(int count) {
        String apiUrl = GNEWS_API_URL + "&apikey=" + gnewsApiKey;
        NewsApiResponse response = restTemplate.getForObject(apiUrl, NewsApiResponse.class);
        return Objects.requireNonNull(response)
            .getArticles()
            .stream()
            .limit(count)
            .collect(Collectors.toList());
    }
    @Cacheable("newsArticles")
    public List<NewsArticle> searchNewsArticles(String keyword) {
        String apiUrl = GNEWS_API_URL.replace("{query}", keyword);
        NewsArticle[] articles = restTemplate.getForObject(apiUrl, NewsArticle[].class);
        return Arrays.asList(articles != null ? articles : new NewsArticle[0]);
    }
}
