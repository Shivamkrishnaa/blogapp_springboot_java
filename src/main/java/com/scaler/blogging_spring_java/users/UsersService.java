package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.users.dtos.CreateUserRequest;
import com.scaler.blogging_spring_java.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(CreateUserRequest req) {
        UserEntity newUser = modelMapper.map(req, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }
    public UserEntity getUser(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserEntity loginUser(LoginUserRequest req) {
        UserEntity user =  usersRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UserNotFoundException(req.getUsername()));
        boolean validUser = passwordEncoder.matches(req.getPassword(),user.getPassword());
        if(!validUser) {
            throw new InvalidCredentialsException();
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
    public static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException(){
            super("Invalid credentials provided");
        }
    }
}
