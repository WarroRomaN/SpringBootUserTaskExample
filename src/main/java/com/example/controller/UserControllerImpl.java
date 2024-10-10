package com.example.controller;

import com.example.dto.UserDTO;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Override
    public List<UserDTO> getAll() {
        return userService.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @PostMapping("/users")
    @Override
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return UserMapper.toDTO(userService.save(UserMapper.toEntity(userDTO)));
    }

    @PostMapping("/users")
    @Override
    public List<UserDTO> save(@RequestBody List<UserDTO> userDTOs) {
        return userService.save(userDTOs.stream()
                        .map(UserMapper::toEntity)
                        .toList()).stream()
                .map(UserMapper::toDTO)
                .toList();
    }


}
