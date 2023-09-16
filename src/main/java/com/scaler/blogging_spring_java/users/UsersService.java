package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.users.dtos.CreateUserRequest;
import com.scaler.blogging_spring_java.users.dtos.LoginUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UserEntity createUser(CreateUserRequest req) {
        var newUser = UserEntity.builder()
                .username(req.getUsername())
//                .password(password) Todo: encrypt password
                .email(req.getEmail())
                .build();
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public Optional<UserEntity> loginUser(LoginUserRequest req) {
        var user = usersRepository.findByUsername(req.getUsername());
        if(user.isEmpty()) {
            throw new UserNotFoundException(req.getUsername());
        }
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with username: "+ username+" not found");
        }
        public UserNotFoundException(Long userId) {
            super("User with id: "+ userId+" not found");
        }
    }
}
