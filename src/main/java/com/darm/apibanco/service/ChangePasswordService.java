package com.darm.apibanco.service;


import com.darm.apibanco.DTO.ChangePasswordRequest;
import com.darm.apibanco.DTO.CodeChangePasswordRequest;
import com.darm.apibanco.model.EmailModel;
import com.darm.apibanco.model.User;
import com.darm.apibanco.model.UserChangePassword;
import com.darm.apibanco.repository.UserChangePasswordRepository;
import com.darm.apibanco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class ChangePasswordService {

    private final UserChangePasswordRepository changePasswordRepository;
    private final PasswordEncoder encoder;

    private final EmailService emailService;

    private final UserRepository userRepository;

    public ChangePasswordService(UserChangePasswordRepository changePasswordRepository, PasswordEncoder encoder, EmailService emailService, UserRepository userRepository) {
        this.changePasswordRepository = changePasswordRepository;
        this.encoder = encoder;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean createRequestToChangePassword(User user) {

        String code = generateCode();

        UserChangePassword userChangePassword = UserChangePassword.builder()
                .code(encoder.encode(code))
                .email(user.getEmail())
                .expiration(LocalDateTime.now().plusMinutes(5))
                .build();

        UserChangePassword changePassword = changePasswordRepository.save(userChangePassword);

        EmailModel emailModel = createEmailModel(changePassword, code);

        return emailService.sendEmailToRecoveryPassword(emailModel);

    }

    public Boolean validateCode(CodeChangePasswordRequest request) {
       return changePasswordRequestIsValid(request.code(), request.email());
    }

    @Transactional
    public User changePassword(ChangePasswordRequest request, User user) {
        user.setPassword(encoder.encode(request.password()));
        return userRepository.save(user);
    }

    private Boolean changePasswordRequestIsValid(String code, String userEmail) {

        String encodedCode = encoder.encode(code);

        return changePasswordRepository.findByCodeAndEmail(encodedCode, userEmail)
                .map(changePassword -> validExpiration(changePassword.getExpiration()))
                .orElse(false);

    }


    private Boolean validExpiration(LocalDateTime expiration) {
        return LocalDateTime.now().isAfter(expiration);
    }

    private EmailModel createEmailModel(UserChangePassword userChangePassword, String code) {
        return new EmailModel(code, "Reset Password", userChangePassword.getEmail());
    }

    private String generateCode() {
        String CHARSET = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {

            int index = random.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(index));

        }
        return sb.toString();
    }

}
