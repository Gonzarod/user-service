package com.evertix.userservice.service.impl;

import com.evertix.userservice.entities.User;
import com.evertix.userservice.exception.ResourceNotFoundException;
import com.evertix.userservice.repository.UserRepository;
import com.evertix.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /*
    @Override
    public Page<User> getAllUsersByCourseId(Long courseId, Pageable pageable) {
        return userRepository.findAllByCoursesId(courseId, pageable);
    }
    */

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User with Id: "+userId+" not found"));
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setName(userDetails.getName());
            user.setLastName(userDetails.getLastName());
            user.setBirthday(userDetails.getBirthday());
            user.setPhone(userDetails.getPhone());
            user.setAddress(userDetails.getAddress());
            user.setTotalStar(userDetails.getTotalStar());
            user.setActive(userDetails.getActive());
            user.setLinkedin(userDetails.getLinkedin());
            return userRepository.save(user);
        }).orElseThrow(()-> new ResourceNotFoundException("User whit Id: "+userId+" not found"));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("User with Id: "+userId+" not found"));
    }
}
