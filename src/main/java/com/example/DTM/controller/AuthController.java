package com.example.DTM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    private AuthenticationManager authenticationManager;
//    private UserDetailsService userDetailsService;
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/register")
//    public String register(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole("ROLE_USER");
//        userRepository.save(user);
//        return "User registered successfully";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody AuthRequest authRequest) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return jwt;
//    }

}
