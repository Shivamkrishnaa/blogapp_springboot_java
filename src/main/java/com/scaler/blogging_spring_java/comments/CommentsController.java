package com.scaler.blogging_spring_java.comments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article/{article-slug}/comments")
public class CommentsController {
    @GetMapping("")
    String fetchComments(@PathVariable("article-slug") String parameter){
        return "Comments";
    }
}
