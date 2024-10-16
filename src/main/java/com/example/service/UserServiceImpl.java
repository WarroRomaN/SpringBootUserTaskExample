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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return UserMapper.toDTO(userRepository.save(UserMapper.toEntity(userDTO)));
    }

    @Override
    public List<UserDTO> save(List<UserDTO> users) {
        return userRepository.saveAll(users.stream()
                        .map(UserMapper::toEntity)
                        .toList()).stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername());

        return UserMapper.toDTO(userRepository.save(existingUser));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO find(long id) {
        return UserMapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id)));
    }

}
