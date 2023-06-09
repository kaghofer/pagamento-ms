package com.example.pagamentoms.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.ArrayList;
import java.util.List;

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
    @Size(max = 10)
    private String expiracao;

    @NotBlank
    @Size(max = 3, min = 3)
    private String codigo;

    @NotNull
    private Long idPedido;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPagamento status;

//    @Enumerated(EnumType.STRING)
//    private TipoPagamento tipoPagamento;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pagamento")
    List<ParcelaPagamento> parcelas = new ArrayList<>();
}