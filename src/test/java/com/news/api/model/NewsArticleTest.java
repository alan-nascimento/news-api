package com.news.api.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewsArticleTest {

    @Test
    void testGettersAndSetters() {
        // Create sample data
        String title = "Test Title";
        String description = "Test Description";
        String content = "Test Content";
        String url = "https://example.com";
        String image = "https://example.com/image.jpg";
        String publishedAt = "2024-04-01T12:00:00Z";
        NewsArticle.Source source = new NewsArticle.Source("Source Name", "https://example.com/source");

        // Create NewsArticle instance
        NewsArticle article = new NewsArticle();
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setUrl(url);
        article.setImage(image);
        article.setPublishedAt(publishedAt);
        article.setSource(source);

        // Verify getters
        assertEquals(title, article.getTitle());
        assertEquals(description, article.getDescription());
        assertEquals(content, article.getContent());
        assertEquals(url, article.getUrl());
        assertEquals(image, article.getImage());
        assertEquals(publishedAt, article.getPublishedAt());
        assertEquals(source, article.getSource());
    }

    @Test
    void testNoArgsConstructor() {
        // Create NewsArticle instance using no-args constructor
        NewsArticle article = new NewsArticle();

        // Verify instance is not null
        assertNotNull(article);

        // Verify default values
        assertEquals(null, article.getTitle());
        assertEquals(null, article.getDescription());
        assertEquals(null, article.getContent());
        assertEquals(null, article.getUrl());
        assertEquals(null, article.getImage());
        assertEquals(null, article.getPublishedAt());
        assertEquals(null, article.getSource());
    }

    @Test
    void testAllArgsConstructor() {
        // Create sample data
        String title = "Test Title";
        String description = "Test Description";
        String content = "Test Content";
        String url = "https://example.com";
        String image = "https://example.com/image.jpg";
        String publishedAt = "2024-04-01T12:00:00Z";
        NewsArticle.Source source = new NewsArticle.Source("Source Name", "https://example.com/source");

        // Create NewsArticle instance using all-args constructor
        NewsArticle article = new NewsArticle(title, description, content, url, image, publishedAt, source);

        // Verify values are set correctly
        assertEquals(title, article.getTitle());
        assertEquals(description, article.getDescription());
        assertEquals(content, article.getContent());
        assertEquals(url, article.getUrl());
        assertEquals(image, article.getImage());
        assertEquals(publishedAt, article.getPublishedAt());
        assertEquals(source, article.getSource());
    }

    @Test
    void testSourceGettersAndSetters() {
        // Create sample data
        String name = "Source Name";
        String url = "https://example.com/source";

        // Create Source instance
        NewsArticle.Source source = new NewsArticle.Source();
        source.setName(name);
        source.setUrl(url);

        // Verify getters
        assertEquals(name, source.getName());
        assertEquals(url, source.getUrl());
    }

    @Test
    void testSourceNoArgsConstructor() {
        // Create Source instance using no-args constructor
        NewsArticle.Source source = new NewsArticle.Source();

        // Verify instance is not null
        assertNotNull(source);

        // Verify default values
        assertEquals(null, source.getName());
        assertEquals(null, source.getUrl());
    }

    @Test
    void testSourceAllArgsConstructor() {
        // Create sample data
        String name = "Source Name";
        String url = "https://example.com/source";

        // Create Source instance using all-args constructor
        NewsArticle.Source source = new NewsArticle.Source(name, url);

        // Verify values are set correctly
        assertEquals(name, source.getName());
        assertEquals(url, source.getUrl());
    }
}
