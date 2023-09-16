package com.scaler.blogging_spring_java.articles;

import com.scaler.blogging_spring_java.articles.dtos.CreateArticleRequest;
import com.scaler.blogging_spring_java.articles.dtos.UpdateArticleRequest;
import com.scaler.blogging_spring_java.users.UserEntity;
import com.scaler.blogging_spring_java.users.UsersRepository;
import com.scaler.blogging_spring_java.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticlesRepository articlesRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ArticleService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }
    public ArticleEntity getArticleBySlug(String slug) {
        return articlesRepository.findBySlug(slug).orElseThrow(() -> new ArticleNotFoundException(slug));
    }

    public ArticleEntity createArticleRequest(CreateArticleRequest req, Long authorId) {
        UserEntity author = usersRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));
        ArticleEntity article = ArticleEntity.builder()
                .title(req.getTitle())
                .subtitle(req.getSubtitle())
                .slug(req.getTitle().toLowerCase().replaceAll("\\s", "-"))
                .body(req.getBody())
                .author(author)
                .build();
        return articlesRepository.save(article);
    }
    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest req) {
        ArticleEntity article = articlesRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
        if(req.getTitle() != null){
            article.setTitle(req.getTitle());
            article.setSlug(req.getTitle().toLowerCase().replaceAll("\\s", "-"));
        }
        if(req.getSubtitle() != null){
            article.setSubtitle(req.getSubtitle());
        }
        if(req.getBody() != null){
            article.setBody(req.getBody());
        }
        if (req.getSlug() != null){
            article.setSlug(req.getSlug());
        }

        return articlesRepository.save(article);
    }
    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("Article by slug: "+slug+" not found.");
        }
        public ArticleNotFoundException(Long id) {
            super("Article by id: "+id+" not found.");
        }
    }
}
