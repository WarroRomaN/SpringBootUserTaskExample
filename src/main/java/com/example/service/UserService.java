package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO save(UserDTO userDTO) {
        return UserMapper.toDTO(userRepository.save(UserMapper.toEntity(userDTO)));
    }

    public List<UserDTO> save(List<UserDTO> users) {
        return userRepository.saveAll(users.stream()
                        .map(UserMapper::toEntity)
                        .toList()).stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));


        existingUser.setFirstName(userDTO.firstName());
        existingUser.setLastName(userDTO.lastName());
        existingUser.setEmail(userDTO.email());
        existingUser.setUsername(userDTO.username());

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public UserDTO find(long id) {
        return UserMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id)));
    }

}
