package com.example.pagamentoms.service;

import com.example.pagamentoms.dto.PagamentoDto;
import com.example.pagamentoms.dto.ParcelaDto;
import com.example.pagamentoms.http.PagamentoHttp;
import com.example.pagamentoms.model.Pagamento;
import com.example.pagamentoms.model.ParcelaPagamento;
import com.example.pagamentoms.model.StatusPagamento;
import com.example.pagamentoms.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoHttp pedido;

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ParcelamentoService serviceParcelas;

    //Retorna todos os pagamentos páginados por em X elementos
    public Page<PagamentoDto> buscaTodos(Pageable pag) {
        return repository
                .findAll(pag)//esse método retorna todas as páginas
                //mas precisa passar pra ele qual classe sera paginada
                .map(p -> modelMapper.map(p, PagamentoDto.class));
        //esse método diz pro FINDALL qual será classe usada para a paginação
    }

    //Esse método retorna um pagamento que corresponde ao ID
    //passado por REQUEST
    public PagamentoDto obterId(Long id) {
        Pagamento pagamento = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    //Quando o método Cadastrar for chamado na CONTROLLER
    //Esse método transforma um dto em PAGAMENTO para poder
    //ser salvo pelo REPOSITORY, que só entende pagamento e
    //não DTO, por fim converte novamente e manda para a CONTROLLER
    public PagamentoDto criarPagamento(PagamentoDto dto) {

        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);

        Pagamento exists = repository.buscaIdPedido(pagamento.getIdPedido());

        if(exists == null){

            pagamento.setStatus(StatusPagamento.AGUARDANDO);

            GerarVencimentos gr = new GerarVencimentos();
            List<ParcelaPagamento> parcelaPagamentos = gr.criarParcelas(dto);
            pagamento.setParcelas(parcelaPagamentos);

            pagamento.getParcelas().forEach(parcela -> parcela.setPagamento(pagamento));
            repository.save(pagamento);

            var mov = modelMapper.map(pagamento, PagamentoDto.class);

            mov.setQtdParcelas(mov.getParcelas().size());

            return mov;
        }
        return null;
    }

    //Passa-se um ID via REQUEST e depois altera o objeto no BODY
    //e envia a alteração para a CONTROLLER gerenciar
    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    //Passa-se um ID via REQUEST e depois deleta ele do banco
    public void deletarPagamento(Long id) {
        repository.deleteById(id);
    }

    public void confirmaPagamento(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);
        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }
        serviceParcelas.pagarTodasParcelas(id);
        pagamento.get().setStatus(StatusPagamento.PAGO);
        repository.save(pagamento.get());
        pedido.atualizarPagamento(pagamento.get().getIdPedido());
    }
    public void cancelarPagamento(Long id) {
        Optional<Pagamento> pagamento = repository.findById(id);
        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }
        serviceParcelas.cancelarTodasParcelas(id);
        pagamento.get().setStatus(StatusPagamento.CANCELADO);
        repository.save(pagamento.get());
        pedido.cancelarPagamento(pagamento.get().getIdPedido());
    }
}