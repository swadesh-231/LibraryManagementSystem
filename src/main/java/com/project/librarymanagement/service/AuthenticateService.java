package com.project.librarymanagement.service;

import com.project.librarymanagement.dto.LoginRequestDTO;
import com.project.librarymanagement.dto.LoginResponseDTO;
import com.project.librarymanagement.dto.RegisterRequestDTO;
import com.project.librarymanagement.dto.UserDTO;

public interface AuthenticateService {
    UserDTO registerNormalUser(RegisterRequestDTO registerRequestDTO);
    UserDTO registerAdmin(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
