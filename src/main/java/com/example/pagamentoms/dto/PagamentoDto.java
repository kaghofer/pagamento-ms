package com.example.pagamentoms.dto;


import com.example.pagamentoms.model.ParcelaPagamento;
import com.example.pagamentoms.model.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDto {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private Long idPedido;
    private StatusPagamento status;
    private int qtdParcelas;
    private List<ParcelaDto> parcelas;
}
