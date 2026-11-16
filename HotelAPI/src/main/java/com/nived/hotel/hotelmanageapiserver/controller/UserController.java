package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.service.UserService;
import com.nived.hotel.hotelmanageapiserver.service.ActivityLogService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;
    private final ActivityLogService activityLogService;

    public UserController(UserService userService,
                          ActivityLogService activityLogService) {
        this.userService = userService;
        this.activityLogService = activityLogService;
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean success = userService.login(
                request.getUsername(),
                request.getPassword()
        );

        if (success) {
            activityLogService.recordLogin(request.getUsername());
            return "Login successful";
        }

        return "Invalid username or password";
    }

    // Simple request class for login
    public static class LoginRequest {

        private String username;
        private String password;

        public LoginRequest() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}