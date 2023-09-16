package com.scaler.blogging_spring_java.articles.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {
    @NotNull
    private String title;
    @NotNull
    private String subtitle;
    @NotNull
    private String body;
    @NotNull
    private String slug;

}
