package com.darm.apibanco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_password_recovery_requests")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserChangePassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, length = 6)
    private LocalDateTime expiration;

    @Column(nullable = false)
    private String email;
}
