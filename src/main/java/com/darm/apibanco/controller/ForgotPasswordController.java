package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.ChangePasswordRequest;
import com.darm.apibanco.DTO.CodeChangePasswordRequest;
import com.darm.apibanco.DTO.ForgotPasswordRequest;
import com.darm.apibanco.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/request-password-reset")
public class ForgotPasswordController {

    private final PersonService personService;

    public ForgotPasswordController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        personService.forgotPassword(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send-code")
    public ResponseEntity<Boolean> sendCode(@RequestBody @Valid CodeChangePasswordRequest request) {
        return ResponseEntity.ok(personService.validateCode(request));
    }

    @PostMapping("/new-password")
    public ResponseEntity<Boolean> setNewPassword(@RequestBody @Valid ChangePasswordRequest request) {
        return ResponseEntity.ok(personService.changePassword(request));
    }


}
