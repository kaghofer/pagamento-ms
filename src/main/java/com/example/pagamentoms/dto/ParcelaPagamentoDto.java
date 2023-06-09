package com.example.pagamentoms.dto;

import com.example.pagamentoms.model.Pagamento;
import com.example.pagamentoms.model.StatusParcela;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ParcelaPagamentoDto {

    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private StatusParcela statusParcela;
    private Pagamento pagamento;
}
