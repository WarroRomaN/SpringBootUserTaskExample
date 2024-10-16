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
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUserShouldReturnCreatedUser() {
        User user = User.builder().username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        User createUser = User.builder().id(123L).username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        given(userRepository.save(user)).willReturn(createUser);

        UserDTO userDTO = UserDTO.builder().username("user1").firstName("firstName").lastName("lastName").email("email.com").build();
        UserDTO createdUserDTO = userService.save(userDTO);

        Assert.isTrue(createdUserDTO.getLastName().equals(userDTO.getLastName()), "Last name not match");
        Assert.isTrue(createdUserDTO.getFirstName().equals(userDTO.getFirstName()), "First name not match");
        Assert.isTrue(createdUserDTO.getEmail().equals(userDTO.getEmail()), "Email not match");
        Assert.isTrue(createdUserDTO.getUsername().equals(userDTO.getUsername()), "Username not match");

    }

}
