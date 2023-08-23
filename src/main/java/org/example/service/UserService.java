package org.example.service;

import org.example.dto.UserDto;
import org.example.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findById(int id);
    User findByEmail(String email);

    List<UserDto> findAllUsers();
}