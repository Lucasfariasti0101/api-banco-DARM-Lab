package com.darm.apibanco.model;

import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_card")
@Getter @Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 19, nullable = false)
    private String number;

    @Column(length = 3, nullable = false)
    private String CVV;

    @Column(length = 50, nullable = false)
    private String flagFinance;

    @Column(length = 20, nullable = false)
    private LocalDate expirationDate;

    @Enumerated(value = EnumType.STRING)
    private CardType type;

    @Enumerated(value = EnumType.STRING)
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public void setNumber(String number) {
        this.number = number;
    }
}
