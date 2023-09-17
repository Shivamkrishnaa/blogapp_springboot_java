package com.scaler.blogging_spring_java.articles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    @GetMapping("")
    String fetchArticles(){
        return "articles";
    }
}
