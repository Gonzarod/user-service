package com.evertix.userservice.service;

import com.evertix.userservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    //Page<User> getAllUsersByCourseId(Long courseId, Pageable pageable);
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long userId);
    User updateUser(Long userId, User userDetails);
    ResponseEntity<?> deleteUser(Long userId);
}
