package com.example.pagamentoms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class ValidarDatasValorDto {

    private List<LocalDate> vencimentos;
    private Double valor;

}
