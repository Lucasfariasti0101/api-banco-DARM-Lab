package com.darm.apibanco.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 8, nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

}
