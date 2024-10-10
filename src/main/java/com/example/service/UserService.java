package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);


    List<User> save(List<User> users);

    List<User> findAll();
}
