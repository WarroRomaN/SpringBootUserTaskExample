package com.example.controller;

import com.example.dto.UserDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.example.constant.Constants.API_USERS;
import static com.example.constant.Constants.SLASH;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {



    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    public static void startContainer() {
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void stopContainer() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driverClassName", postgreSQLContainer::getDriverClassName);
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldCreateUser() {
        UserDTO userDTO = UserDTO.builder().id(1L).username("userVictoria").email("Victoria.Halimon@gmail.com").firstName("Victoria").lastName("Halimon").build();

        ResponseEntity<UserDTO> createdUserDTOResponseEntity = testRestTemplate.postForEntity(
                API_USERS, userDTO, UserDTO.class);

        assertThat(createdUserDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        UserDTO createdUserDTO = createdUserDTOResponseEntity.getBody();
        assertThat(createdUserDTO).isNotNull();
        assert createdUserDTO != null;
        assertThat(createdUserDTO.firstName()).isEqualTo(userDTO.firstName());
        assertThat(createdUserDTO.lastName()).isEqualTo(userDTO.lastName());
        assertThat(createdUserDTO.email()).isEqualTo(userDTO.email());
        assertThat(createdUserDTO.username()).isEqualTo(userDTO.username());

        ResponseEntity<UserDTO> foundedUserDTOResponseEntity = testRestTemplate.getForEntity(
                API_USERS + SLASH + createdUserDTO.id(), UserDTO.class);

        UserDTO foundedUserDTO = foundedUserDTOResponseEntity.getBody();
        assertThat(foundedUserDTO).isNotNull();
        assert foundedUserDTO != null;
        assertThat(foundedUserDTO.id()).isEqualTo(userDTO.id());
        assertThat(foundedUserDTO.firstName()).isEqualTo(userDTO.firstName());
        assertThat(foundedUserDTO.lastName()).isEqualTo(userDTO.lastName());
        assertThat(foundedUserDTO.email()).isEqualTo(userDTO.email());
        assertThat(foundedUserDTO.username()).isEqualTo(userDTO.username());
    }




}
