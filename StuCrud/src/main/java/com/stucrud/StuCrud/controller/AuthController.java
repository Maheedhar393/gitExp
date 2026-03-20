package com.stucrud.StuCrud.controller;


import com.stucrud.StuCrud.entity.AuthenticationUser;
import com.stucrud.StuCrud.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthenticationUser autehticationUser){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    autehticationUser.getUsername(), autehticationUser.getPassword())
            );
        }
        catch (Exception e) {
            System.out.println("Exception occurred during authentication: " + e.getMessage());
            throw e;
        }
        return jwtUtil.generateToken(autehticationUser.getUsername());
    }

}
