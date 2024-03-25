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

    @GetMapping
    public List<NewsArticle> getNewsArticles(@RequestParam(defaultValue = "10") int count) {
        return newsService.fetchNewsArticles(count);
    }

    @GetMapping("/search")
    public List<NewsArticle> searchNewsArticles(@RequestParam String keyword) {
        return newsService.searchNewsArticles(keyword);
    }

//    @GetMapping("/{id}")
//    public NewsArticle getNewsArticleById(@PathVariable long id) {
//        return newsService.findNewsArticleById(id);
//    }
//
//    @GetMapping("/title/{title}")
//    public NewsArticle getNewsArticleByTitle(@PathVariable String title) {
//        return newsService.findNewsArticleByTitle(title);
//    }
//
//    @GetMapping("/author/{author}")
//    public List<NewsArticle> getNewsArticlesByAuthor(@PathVariable String author) {
//        return newsService.findNewsArticlesByAuthor(author);
//    }
}
