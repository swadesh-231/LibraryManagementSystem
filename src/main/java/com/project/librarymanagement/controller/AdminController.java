package com.project.librarymanagement.controller;

import com.project.librarymanagement.dto.RegisterRequestDTO;
import com.project.librarymanagement.dto.UserDTO;
import com.project.librarymanagement.service.AuthenticateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AuthenticateService authenticateService;

    public AdminController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping("/register-admin-user")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticateService.registerAdmin(registerRequestDTO));
    }

}
