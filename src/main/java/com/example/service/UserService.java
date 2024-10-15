package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;

import java.util.List;

public interface UserService {

    UserDTO save(UserDTO user);

    List<UserDTO> save(List<UserDTO> users);

    List<UserDTO> findAll();

    UserDTO update(Long id, UserDTO user);

    void delete(long id);

    UserDTO find(long id);
}
