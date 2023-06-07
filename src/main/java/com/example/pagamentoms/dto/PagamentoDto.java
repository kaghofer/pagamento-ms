package com.example.pagamentoms.dto;


import com.example.pagamentoms.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class PagamentoDto {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private Long idPedido;
    private Long formaDePagamentoId;
    private Status status;
}
