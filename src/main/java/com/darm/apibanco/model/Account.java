package com.darm.apibanco.model;

import com.darm.apibanco.model.enums.AccountType;
import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "account")
    @JoinColumn
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public AccountType getType() {
        return type;
    }
}
