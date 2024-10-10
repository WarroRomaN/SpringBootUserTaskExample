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
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

}
