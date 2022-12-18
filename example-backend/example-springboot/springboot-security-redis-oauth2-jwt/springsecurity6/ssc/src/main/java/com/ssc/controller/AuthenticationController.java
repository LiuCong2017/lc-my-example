package com.ssc.controller;

import com.ssc.config.JwtTools;
import com.ssc.dao.UserDao;
import com.ssc.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userService;
    private final JwtTools jwtTools;

    //http://localhost:8999/api/authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        final UserDetails userDetails = userService.findUserByEmail(request.getEmail());
        System.out.printf(userDetails.getUsername());
        if (userDetails != null){
            return ResponseEntity.ok(jwtTools.generateToken(userDetails));
        }

        return ResponseEntity.status(400).body("Some error has occurred");
    }

}
