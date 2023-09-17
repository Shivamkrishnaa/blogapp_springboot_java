package com.scaler.blogging_spring_java.users;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    @Order(1)
    void can_create_users(){
        var user = modelMapper.map(
                new UserEntity("shivam","123","shivamkrishna96@gmail.com"),
                UserEntity.class);
        usersRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_user(){
        var user = modelMapper.map(
                new UserEntity("shivam","123","shivamkrishna96@gmail.com"),
                UserEntity.class);
        usersRepository.save(user);
        var users = usersRepository.findAll();
        assert users.size() == 1;
    }
}
