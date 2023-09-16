package com.scaler.blogging_spring_java.articles;

import com.scaler.blogging_spring_java.users.UserEntity;
import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity(name="articles")
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column(unique = true)
    private String slug;

    @Column(nullable = false)
    @NonNull
    private String subtitle;

    @Column(nullable = false)
    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    private UserEntity author;
}
