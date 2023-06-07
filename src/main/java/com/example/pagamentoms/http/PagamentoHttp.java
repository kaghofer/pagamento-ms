package com.example.pagamentoms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

    @FeignClient("pedido-ms")
    public interface PagamentoHttp{
        @RequestMapping(method = RequestMethod.PUT, value = "/pedido/{id}/pago")
        void atualizarPagamento(@PathVariable Long id);
    }