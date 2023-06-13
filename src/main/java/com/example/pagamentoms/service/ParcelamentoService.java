package com.example.pagamentoms.service;

import com.example.pagamentoms.dto.PagamentoDto;
import com.example.pagamentoms.dto.ParcelaPagamentoDto;
import com.example.pagamentoms.http.PagamentoHttp;
import com.example.pagamentoms.model.ParcelaPagamento;
import com.example.pagamentoms.model.StatusParcela;
import com.example.pagamentoms.repository.PagamentoRepository;
import com.example.pagamentoms.repository.ParcelasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParcelamentoService {

    @Autowired
    private PagamentoHttp pedido;

    @Autowired
    private ParcelasRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ParcelaPagamentoDto> buscaPorIdPagamento(Long id) {
        return repository
                .buscarParcelasPagamento(id).stream().map(f -> modelMapper.map(f, ParcelaPagamentoDto.class)).toList();
    }

    public void pagarTodasParcelas(Long id) {
        List<ParcelaPagamento> lista = repository.buscarParcelasPagamento(id);
        for (ParcelaPagamento p : lista) {
            p.setStatusParcela(StatusParcela.PAGO);
            p.setDataPagamento(LocalDateTime.now());
            repository.save(p);
        }
    }

}
