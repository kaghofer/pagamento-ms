package com.example.pagamentoms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    private BigDecimal valor;

    @Size(max=100)
    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 20)
    private String numero;

    @NotBlank
    @Size(max = 8)
    private String expiracao;

    @NotBlank
    @Size(max = 3, min = 3)
    private String codigo;

    @NotNull
    private Long idPedido;

    @NotNull
    private Long formaDePagamentoId;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;
}