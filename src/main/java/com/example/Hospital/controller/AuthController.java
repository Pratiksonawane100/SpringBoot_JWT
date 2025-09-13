package com.example.Hospital.controller;

import com.example.Hospital.Security.AuthService;
import com.example.Hospital.entity.LoginRequestDTO;
import com.example.Hospital.entity.LoginResponseDTO;
import com.example.Hospital.entity.SignupResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody
                                                      LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.signup(loginRequestDTO));
    }
}
