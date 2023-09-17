package com.scaler.blogging_spring_java.users;

import com.scaler.blogging_spring_java.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase()
public class UsersServiceTest {
    @Autowired
    UsersService usersService;
    @Test
    void can_create_user(){
        UserEntity user = usersService.createUser(new CreateUserRequest(
                "shivam","123", "shivamkrishna96@gmail.com"
        ));
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getUsername(), "shivam");
    }
}
