package com.example.pagamentoms.controller;

import com.example.pagamentoms.dto.PagamentoDto;
import com.example.pagamentoms.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @GetMapping
    public Page<PagamentoDto> listar(@PageableDefault (size = 10)Pageable pag){
        return service.buscaTodos(pag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> buscar(@PathVariable @NotNull Long id){
        PagamentoDto dto = service.obterId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizar(@PathVariable @NotNull Long id, @Valid @RequestBody PagamentoDto dto){
        PagamentoDto atualizado = service.atualizarPagamento(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> excluir(@PathVariable @NotNull Long id){
        service.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto, UriComponentsBuilder uri){
        PagamentoDto pagamentoDto = service.criarPagamento(dto);
        URI endereco = uri.path("/pagamento/{id}").buildAndExpand(pagamentoDto.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamentoDto);
    }

    @PatchMapping("/{id}/confirmar")
    public void confirmaPagamento(@PathVariable @NotNull Long id){
        service.confirmaPagamento(id);
    }
}