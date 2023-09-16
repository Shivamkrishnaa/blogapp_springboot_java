package com.scaler.blogging_spring_java.comments;

import com.scaler.blogging_spring_java.articles.ArticleEntity;
import com.scaler.blogging_spring_java.users.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false)
    @NonNull
    private String body;

    @Column(nullable = false)
    @NonNull
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="articleId", nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;
}
