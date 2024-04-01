package com.news.api.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewsSearchApiParamsBuilderTest {

    @Test
    public void testBuild() {
        // Mocking the parameters
        String gnewsApiUrl = "https://example.com/api";
        String gnewsApiKey = "api_key";
        String query = "test";
        String title = "test_title";
        String author = "test_author";
        int count = 10;

        // Create an instance of the builder
        NewsSearchApiParamsBuilder builder = NewsSearchApiParamsBuilder.builder()
            .gnewsApiUrl(gnewsApiUrl)
            .gnewsApiKey(gnewsApiKey)
            .query(query)
            .title(title)
            .author(author)
            .count(count)
            .build();

        // Build the expected API URL
        StringBuilder expectedUrlBuilder = new StringBuilder(gnewsApiUrl)
            .append("?apikey=").append(gnewsApiKey)
            .append("&max=").append(count)
            .append("&q=").append(query)
            .append("&title=").append(title)
            .append("&author=").append(author);

        // Call the build method
        String apiUrl = builder.build();

        // Verify that the apiUrl is built correctly
        assertEquals(expectedUrlBuilder.toString(), apiUrl);
    }

    @Test
    public void testBuildWithNullValues() {
        // Mocking the parameters
        String gnewsApiUrl = "https://example.com/api";
        String gnewsApiKey = "api_key";
        int count = 10;

        // Create an instance of the builder with null values
        NewsSearchApiParamsBuilder builder = NewsSearchApiParamsBuilder.builder()
            .gnewsApiUrl(gnewsApiUrl)
            .gnewsApiKey(gnewsApiKey)
            .count(count)
            .build();

        // Build the expected API URL
        StringBuilder expectedUrlBuilder = new StringBuilder(gnewsApiUrl)
            .append("?apikey=").append(gnewsApiKey)
            .append("&max=").append(count);

        // Call the build method
        String apiUrl = builder.build();

        // Verify that the apiUrl is built correctly
        assertEquals(expectedUrlBuilder.toString(), apiUrl);
    }
}
