package com.scaler.blogging_spring_java.articles;

import com.scaler.blogging_spring_java.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticlesRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findBySlug(String slug);
    Optional<ArticleEntity> findById(Long id);
}
