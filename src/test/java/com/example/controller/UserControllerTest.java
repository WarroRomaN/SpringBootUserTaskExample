package com.example.controller;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        userRepository.deleteAll();
    }

    @Test
    void shouldGetAllUsers() {
        List<User> users = List.of(
                User.builder().username("userRoman").email("Roman.Halimon@gmail.com").firstName("Roman").lastName("Halimon").build(),
                User.builder().username("userAnna").email("Anna.Ostapenko@outlook.com").firstName("Anna").lastName("Ostapenko").build()
        );
        userRepository.saveAll(users);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body(".", hasSize(2));
    }

    @Test
    void shouldCreateUsers() {
        UserDTO user1 = UserDTO.builder().id(1L).username("userVictoria").email("Victoria.Halimon@gmail.com").firstName("Victoria").lastName("Halimon").build();
        given()
                .contentType(ContentType.JSON)
                .body(user1)
                .when()
                .post("/api/users")
                .then()
                .statusCode(200);

        UserDTO user2 = UserDTO.builder().id(2L).username("userVictor").email("Victor.Ostapenko@outlook.com").firstName("Victor").lastName("Ostapenko").build();
        given()
                .contentType(ContentType.JSON)
                .body(user2)
                .when()
                .post("/api/users")
                .then()
                .statusCode(200);


        UserDTO user3 = UserDTO.builder().id(3L).username("userPavel").email("Pavel.Sarmar@outlook.com").firstName("Pavel").lastName("Sarmar").build();
        given()
                .contentType(ContentType.JSON)
                .body(user3)
                .when()
                .post("/api/users")
                .then()
                .statusCode(200);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body(".", hasSize(3));
    }

}
