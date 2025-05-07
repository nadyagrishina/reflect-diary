package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.DTO.UserRequestDTO;
import com.nadyagrishina.reflect_diary.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAllUsers();
    UserResponseDTO findById(Long id);
    UserResponseDTO save(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void delete(Long id);
    UserResponseDTO findByUsername(String username);
}
