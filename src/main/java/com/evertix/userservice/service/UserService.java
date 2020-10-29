package com.evertix.userservice.service;

import com.evertix.userservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    Page<User> getAllUsersPage(Pageable pageable);

    User getUserById(Long userId);
    User getUserByUsername(String username);
}
