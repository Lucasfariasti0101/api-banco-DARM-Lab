package com.darm.apibanco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID idCartao;

    @NonNull
    @NotBlank
    @Size(max = 19)
    private String numero;

    @NonNull
    @NotBlank
    @Size(max = 20)
    private String bandeira;

    @NonNull
    @NotBlank
    @Size(max = 3)
    private String cvc;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private TipoDeCartao tipoDeCartao;

    @NonNull
    @Enumerated(value = EnumType.STRING)
    private StatusCartao statusCartao = StatusCartao.PENDENTE;

    @NonNull
    @NotBlank
    @Size(min = 5, max = 16)
    private String dataDeValidade;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id")
    private Cliente cliente;

    public String data (int qtdAnos) {
        LocalDate dataValidade = LocalDate.now().plusYears(qtdAnos);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        return dataValidade.format(formatter);

    }

}
