package com.okwatch.flightreservation.services;

import com.okwatch.flightreservation.controller.FlightController;
import com.okwatch.flightreservation.entities.User;
import com.okwatch.flightreservation.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);


        if(user == null) {
            throw new UsernameNotFoundException("User not found for email: " + username);
        }
        logger.debug(user.getEmail());
        System.out.println("user details " + user.toString());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());

    }

}
