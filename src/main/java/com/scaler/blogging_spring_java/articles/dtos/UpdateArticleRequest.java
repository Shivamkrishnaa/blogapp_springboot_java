package com.scaler.blogging_spring_java.articles.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {
    @Nullable
    private String title;
    @Nullable
    private String subtitle;
    @Nullable
    private String body;
    @Nullable
    private String slug;
}
