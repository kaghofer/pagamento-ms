package com.example.pagamentoms.repository;

import com.example.pagamentoms.model.Pagamento;
import com.example.pagamentoms.model.ParcelaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParcelasRepository extends JpaRepository<ParcelaPagamento, Long> {

    @Query(value = "select p from ParcelaPagamento p where p.pagamento.id = :id")
    List<ParcelaPagamento> buscarParcelasPagamento(Long id);

}
