package com.example.pagamentoms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataCriacao;

    @NotNull
    private LocalDateTime dataVencimento;

    private LocalDateTime dataPagamento;

    @NotNull
    private StatusParcela statusParcela;

    @ManyToOne(optional = false)
    private Pagamento pagamento;
}
