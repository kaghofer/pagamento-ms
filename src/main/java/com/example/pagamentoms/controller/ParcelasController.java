package com.example.pagamentoms.controller;

import com.example.pagamentoms.dto.PagamentoDto;
import com.example.pagamentoms.dto.ParcelaPagamentoDto;
import com.example.pagamentoms.dto.ParcelaValorDto;
import com.example.pagamentoms.dto.ValidarDatasValorDto;
import com.example.pagamentoms.model.ParcelaPagamento;
import com.example.pagamentoms.model.StatusParcela;
import com.example.pagamentoms.repository.PagamentoRepository;
import com.example.pagamentoms.repository.ParcelasRepository;
import com.example.pagamentoms.service.GerarVencimentos;
import com.example.pagamentoms.service.PagamentoService;
import com.example.pagamentoms.service.ParcelamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("parcelamento")
public class ParcelasController {

    @Autowired
    private GerarVencimentos gerarVencimentos;

    @Autowired
    private ParcelamentoService service;

    @PostMapping("/simular")
    public List<ParcelaValorDto> simularParcelanto(@RequestBody ParcelaValorDto parcelaValor) {
        return gerarVencimentos.gerarDatasVencimento(parcelaValor);
    }

    @PostMapping("/validar")
    public String validarParcelanto(@RequestBody ValidarDatasValorDto validarDatasValorDto) {
        return null;
    }

    @GetMapping("/listarparcelas/{id}")
    public ResponseEntity<List<ParcelaPagamentoDto>> listarParcelas(@PathVariable Long id) {
        List<ParcelaPagamentoDto> lista = service.buscaPorIdPagamento(id);

        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/{id}/confirmar")
    public void confirmaPagamento(@PathVariable @NotNull Long id) {
        service.pagarTodasParcelas(id);
    }


}
