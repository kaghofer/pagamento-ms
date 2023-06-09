package com.example.pagamentoms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Data
public class ParcelaDto {
    private Long id;
    private double valor;
    private LocalDateTime dataVencimento;

}
