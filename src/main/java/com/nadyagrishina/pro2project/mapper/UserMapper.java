package com.nadyagrishina.pro2project.mapper;

import com.nadyagrishina.pro2project.DTO.UserRequestDTO;
import com.nadyagrishina.pro2project.DTO.UserResponseDTO;
import com.nadyagrishina.pro2project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponseDTO toDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public void updateUserFromDTO (User user, UserRequestDTO userRequestDTO) {
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
    }
}
