package com.scaler.blogging_spring_java.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
}
