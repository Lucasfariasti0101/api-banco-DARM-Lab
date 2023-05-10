package com.darm.apibanco.model;

import com.darm.apibanco.model.enums.SolicitationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CardSolicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Card.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private SolicitationStatus status;

}
