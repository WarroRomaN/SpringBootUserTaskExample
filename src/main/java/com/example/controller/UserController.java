package com.example.controller;

import com.example.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    @GetMapping
    List<UserDTO> getAll();

    @PostMapping("/employees")
    UserDTO save(@RequestBody UserDTO userDTO);

    @PostMapping("/users")
    List<UserDTO> save(@RequestBody List<UserDTO> userDTOs);
}
