package com.example.pagamentoms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelaValorDto {
    private int num;
    private Double valor;
    private LocalDate data;

}
