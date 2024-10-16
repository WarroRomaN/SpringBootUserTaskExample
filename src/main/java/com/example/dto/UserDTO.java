package com.example.dto;

import lombok.Builder;

@Builder
public record UserDTO(Long id, String username, String firstName, String lastName, String email) {
}
