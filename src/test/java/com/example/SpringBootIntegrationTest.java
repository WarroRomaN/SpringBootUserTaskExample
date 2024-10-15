package com.example;

import com.example.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootIntegrationTest {

    final private TestRestTemplate restTemplate;

    @Autowired
    public SpringBootIntegrationTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Test
    public void saveUserTestShouldReturnCreatedUser() {
        UserDTO userTestDTO = UserDTO.builder()
                .username("usertest1")
                .password("usertest1")
                .lastName("Roberts")
                .firstName("Emily")
                .email("Emily.Roberts@gmail.com")
                .build();

        ResponseEntity<UserDTO> createdUserDTOResponseEntity = restTemplate.postForEntity("/api/users", userTestDTO, UserDTO.class);
        UserDTO createdUserDTO = createdUserDTOResponseEntity.getBody();

        Assert.notNull(createdUserDTO, "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getEmail().equals(userTestDTO.getEmail()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getUsername().equals(userTestDTO.getUsername()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getPassword().equals(userTestDTO.getPassword()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getFirstName().equals(userTestDTO.getFirstName()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getLastName().equals(userTestDTO.getLastName()), "User was not created via rest template");

        ResponseEntity<UserDTO> foundUserDTOResponse = restTemplate.getForEntity("/api/users/", UserDTO.class);
        UserDTO foundUserDTO = foundUserDTOResponse.getBody();

        Assert.notNull(foundUserDTO, "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getEmail().equals(foundUserDTO.getEmail()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getUsername().equals(foundUserDTO.getUsername()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getPassword().equals(foundUserDTO.getPassword()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getFirstName().equals(foundUserDTO.getFirstName()), "User was not created via rest template");
        Assert.isTrue(createdUserDTO.getLastName().equals(foundUserDTO.getLastName()), "User was not created via rest template");

    }

}
