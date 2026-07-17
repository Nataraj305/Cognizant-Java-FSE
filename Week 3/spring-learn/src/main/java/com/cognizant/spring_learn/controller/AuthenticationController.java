package com.cognizant.spring_learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cognizant.spring_learn.auth.AuthenticationRequest;
import com.cognizant.spring_learn.auth.AuthenticationResponse;
import com.cognizant.spring_learn.util.JwtUtil;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request) {

        if ("user".equals(request.getUsername())
                && "pwd".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername());

            return new AuthenticationResponse(token);
        }

        return new AuthenticationResponse("Invalid Credentials");
    }
}