package com.news.api.controller;

import com.news.api.model.NewsArticle;
import com.news.api.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // TODO: Add request params validation messages
    @GetMapping("/search")
    public List<NewsArticle> getNewsArticles(@RequestParam() String query,
                                             @RequestParam(required = false) String title,
                                             @RequestParam(required = false) String author,
                                             @RequestParam(defaultValue = "10") int count) {
        return newsService.findNewsArticles(query, title, author, count);
    }
}
