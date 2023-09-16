package com.scaler.blogging_spring_java.users.dtos;


import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Setter(AccessLevel.NONE)
public class LoginUserRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
