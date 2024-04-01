package com.news.api.model;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewsApiResponseTest {

    @Test
    void testGettersAndSetters() {
        // Create sample data
        int totalArticles = 10;
        List<NewsArticle> articles = new ArrayList<>();

        // Create NewsApiResponse instance
        NewsApiResponse response = new NewsApiResponse();
        response.setTotalArticles(totalArticles);
        response.setArticles(articles);

        // Verify getters
        assertEquals(totalArticles, response.getTotalArticles());
        assertEquals(articles, response.getArticles());
    }

    @Test
    void testNoArgsConstructor() {
        // Create NewsApiResponse instance using no-args constructor
        NewsApiResponse response = new NewsApiResponse();

        // Verify instance is not null
        assertNotNull(response);

        // Verify default values
        assertEquals(0, response.getTotalArticles());
        assertEquals(null, response.getArticles());
    }

    @Test
    void testAllArgsConstructor() {
        // Create sample data
        int totalArticles = 10;
        List<NewsArticle> articles = new ArrayList<>();

        // Create NewsApiResponse instance using all-args constructor
        NewsApiResponse response = new NewsApiResponse(totalArticles, articles);

        // Verify values are set correctly
        assertEquals(totalArticles, response.getTotalArticles());
        assertEquals(articles, response.getArticles());
    }
}
