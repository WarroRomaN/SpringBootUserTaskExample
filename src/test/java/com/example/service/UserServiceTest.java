package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUserShouldReturnCreatedUser() {
        User user = User.builder().username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        User createUser = User.builder().id(123L).username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        given(userRepository.save(user)).willReturn(createUser);

        UserDTO userDTO = UserDTO.builder().username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        UserDTO createdUserDTO = userService.save(userDTO);

        Assert.isTrue(createdUserDTO.lastName().equals(userDTO.lastName()), "Last name not match");
        Assert.isTrue(createdUserDTO.firstName().equals(userDTO.firstName()), "First name not match");
        Assert.isTrue(createdUserDTO.email().equals(userDTO.email()), "Email not match");
        Assert.isTrue(createdUserDTO.username().equals(userDTO.username()), "Username not match");

    }

}
