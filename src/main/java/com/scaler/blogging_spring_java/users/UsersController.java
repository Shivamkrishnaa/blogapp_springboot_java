package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.common.dtos.ErrorResponse;
import com.scaler.blogging_spring_java.security.JWTService;
import com.scaler.blogging_spring_java.users.dtos.CreateUserRequest;
import com.scaler.blogging_spring_java.users.dtos.UserResponse;
import com.scaler.blogging_spring_java.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    public final ModelMapper modelMapper;
    private final JWTService jwtService;
    public UsersController(UsersService usersService, ModelMapper modelMapper, JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signupUsers(@RequestBody CreateUserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserURI = URI.create("/users/"+savedUser.getId());
        var savedUserResponse = modelMapper.map(savedUser, UserResponse.class);
        String token = jwtService.createJwt(savedUser.getId());
        savedUserResponse.setToken(token);
        return ResponseEntity.created(savedUserURI).body(savedUserResponse);
    }
    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUsers(@RequestBody LoginUserRequest request){
        UserEntity loginUser = usersService.loginUser(request);
        var loginUserResponse = modelMapper.map(loginUser, UserResponse.class);
        String token = jwtService.createJwt(loginUser.getId());
        loginUserResponse.setToken(token);
        return ResponseEntity.ok().body(loginUserResponse);
    }
    @ExceptionHandler({
            UsersService.UserNotFoundException.class,
            UsersService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse> handleInvalidCredentials(Exception ex) {
        String message;
        HttpStatus httpStatus;
        if(ex instanceof UsersService.UserNotFoundException){
            message = ex.getMessage();
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof UsersService.InvalidCredentialsException){
            message = ex.getMessage();
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else {
            message = "Internal server error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
