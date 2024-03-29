package com.news.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsApiResponse {

    private int totalArticles;

    private List<NewsArticle> articles;
}
