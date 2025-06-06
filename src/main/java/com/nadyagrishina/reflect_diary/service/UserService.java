package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findById(Long id);
    User save(User user);
    User updateUser(Long id, User user);
    void delete(Long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
