package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.users.dtos.CreateUserRequest;
import com.scaler.blogging_spring_java.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public UserEntity createUser(CreateUserRequest req) {
        UserEntity newUser = modelMapper.map(req, UserEntity.class);
//        var newUser = UserEntity.builder()
//                .username(req.getUsername())
//                .password(req.getPassword())
//                .email(req.getEmail())
//                .build();
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserEntity loginUser(LoginUserRequest req) {
        return usersRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UserNotFoundException(req.getUsername()));
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
