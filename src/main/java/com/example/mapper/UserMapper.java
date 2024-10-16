package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
    }

}
