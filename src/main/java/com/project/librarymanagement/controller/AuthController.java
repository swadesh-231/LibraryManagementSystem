package com.project.librarymanagement.controller;

import com.project.librarymanagement.dto.LoginRequestDTO;
import com.project.librarymanagement.dto.LoginResponseDTO;
import com.project.librarymanagement.dto.RegisterRequestDTO;
import com.project.librarymanagement.dto.UserDTO;
import com.project.librarymanagement.service.AuthenticateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticateService authenticateService;
    public AuthController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }
    @PostMapping("/register-normal-user")
    public ResponseEntity<UserDTO> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticateService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authenticateService.login(loginRequestDTO));
    }
}
