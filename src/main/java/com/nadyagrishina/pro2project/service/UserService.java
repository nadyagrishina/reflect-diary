package com.nadyagrishina.pro2project.service;

import com.nadyagrishina.pro2project.DTO.UserRequestDTO;
import com.nadyagrishina.pro2project.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> findAllUsers();
    UserResponseDTO findById(Long id);
    UserResponseDTO save(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void delete(Long id);
    UserResponseDTO findByUsername(String username);
}
