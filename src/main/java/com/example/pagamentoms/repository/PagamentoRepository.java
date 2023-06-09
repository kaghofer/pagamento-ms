package com.example.pagamentoms.repository;


import com.example.pagamentoms.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    @Query(value = "select p from Pagamento p where p.idPedido = :id")
    Pagamento buscaIdPedido(Long id);



}
