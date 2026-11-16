package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.entity.User;
import com.nived.hotel.hotelmanageapiserver.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Validate login credentials
    public boolean login(String username, String password) {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            return false;
        }

        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}