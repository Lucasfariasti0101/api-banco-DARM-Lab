package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.AuthenticationRequest;
import com.darm.apibanco.DTO.AuthenticationResponse;
import com.darm.apibanco.DTO.RegisterUserRequest;
import com.darm.apibanco.model.enums.Role;
import com.darm.apibanco.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterUserRequest request) {
        return ResponseEntity.ok(authService.register(request, Role.USER));
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody @Valid RegisterUserRequest request) {

        return ResponseEntity.ok(authService.register(request, Role.ADMIN));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));

    }

}
