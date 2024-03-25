package com.news.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticle {
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private String publishedAt;
    private Source source;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Source {
        private String name;
        private String url;
    }
}
