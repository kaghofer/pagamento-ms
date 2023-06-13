package com.example.pagamentoms.service;

import com.example.pagamentoms.dto.PagamentoDto;
import com.example.pagamentoms.dto.ParcelaDto;
import com.example.pagamentoms.dto.ParcelaValorDto;
import com.example.pagamentoms.model.Pagamento;
import com.example.pagamentoms.model.ParcelaPagamento;
import com.example.pagamentoms.model.StatusParcela;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GerarVencimentos {

    @Autowired
    private ModelMapper modelMapper;

    public List<ParcelaValorDto> gerarDatasVencimento(@RequestBody ParcelaValorDto parcelaValor) {

        List<ParcelaValorDto> parcelas = new ArrayList<>();

        Double vlParcela = parcelaValor.getValor() / parcelaValor.getNum();

        LocalDate temp = LocalDate.now();
        for (int i = 0; i < parcelaValor.getNum(); i++) {
            ParcelaValorDto pr = new ParcelaValorDto(i + 1, vlParcela, temp);
            parcelas.add(pr);
            temp = temp.plusDays(30);
        }
        return parcelas;
    }

    public List<ParcelaPagamento> criarParcelas(PagamentoDto pagamentoDto) {

        List<ParcelaPagamento> parcelas = new ArrayList<>();

        //var vl1 = pagamentoDto.getValor().divide(BigDecimal.valueOf(pagamentoDto.getQtdParcelas()));

        BigDecimal vlParcela = pagamentoDto.getValor()
                .divide(BigDecimal.valueOf(pagamentoDto.getQtdParcelas()), 2, RoundingMode.HALF_UP);

        LocalDate temp = LocalDate.now();
        for (int i = 0; i < pagamentoDto.getQtdParcelas(); i++) {
            ParcelaPagamento pr = new ParcelaPagamento();
            //Pagamento pag = modelMapper.map(pagamentoDto, Pagamento.class);
            //pr.setPagamento(pag);
            pr.setDataVencimento(temp.atTime(0,0,0));
            pr.setValor(vlParcela);
            pr.setDataCriacao(LocalDateTime.now());
            pr.setStatusParcela(StatusParcela.EM_ABERTO);
            parcelas.add(pr);
            temp = temp.plusDays(30);
        }


        return parcelas;
    }




//    public String verificarValorVencimentos(ValidarDatasValorDto validarDatasValorDto) {
//
//        List<LocalDate> lista1 = gerarDatasVencimento(validarDatasValorDto.getValor());
//
//        int size = validarDatasValorDto.getVencimentos().size();
//        for (int i = 0; i < size; i++) {
//            if (!(validarDatasValorDto.getVencimentos().get(i).equals(lista1.get(i)))) {
//                return "Datas de vencimentos invalidas";
//            }
//        }
//        return null;
//    }

}
