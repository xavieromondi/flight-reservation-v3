package com.okwatch.flightreservation.controller;

import com.okwatch.flightreservation.entities.User;
import com.okwatch.flightreservation.repos.UserRepository;
import com.okwatch.flightreservation.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "User already exists with email: " + user.getEmail();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        boolean login = securityService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return login ? "Login successful" : "Invalid username or password";
    }
}
