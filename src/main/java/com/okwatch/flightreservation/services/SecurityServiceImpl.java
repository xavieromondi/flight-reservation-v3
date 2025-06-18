package com.okwatch.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;


    @Override
    public boolean login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        boolean authenticated = token.isAuthenticated();
        if(authenticated){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return authenticated;
    }
}
