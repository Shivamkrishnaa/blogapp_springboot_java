package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.common.dtos.ErrorResponse;
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

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    ResponseEntity<UserResponse> signupUsers(@RequestBody CreateUserRequest request){
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserURI = URI.create("/users/"+savedUser.getId());
        return ResponseEntity.created(savedUserURI).body(modelMapper.map(savedUser, UserResponse.class));
    }
    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUsers(@RequestBody LoginUserRequest request){
        UserEntity loginUser = usersService.loginUser(request);
        return ResponseEntity.ok().body(modelMapper.map(loginUser, UserResponse.class));
    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class
    })
    ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex) {
        String message;
        HttpStatus httpStatus;
        if(ex instanceof UsersService.UserNotFoundException){
            message = ex.getMessage();
            httpStatus = HttpStatus.NOT_FOUND;
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
//                    .message(ex.getMessage())
//                    .build());
        } else {
            message = "Internal server error";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(message)
                .build();
//        return ResponseEntity.internalServerError().body(errorResponse);

        return ResponseEntity.status(httpStatus).body(errorResponse);

    }
}
