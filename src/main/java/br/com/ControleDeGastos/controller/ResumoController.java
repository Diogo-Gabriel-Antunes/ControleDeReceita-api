package br.com.ControleDeGastos.controller;

import br.com.ControleDeGastos.Model.Despesas;
import br.com.ControleDeGastos.Model.Receitas;
import br.com.ControleDeGastos.Model.Resumo;
import br.com.ControleDeGastos.Vo.RelatorioCategoriaVo;
import br.com.ControleDeGastos.repository.DespesasRepository;
import br.com.ControleDeGastos.repository.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private DespesasRepository despesasRepository;

    @GetMapping("/{ano}/{mes}")
    public Resumo resumoDoMes(@PathVariable int ano,@PathVariable int mes){
        List<Receitas> receitasRepositoryByData = receitasRepository.findByData(ano, mes);
        List<Despesas> despesasRepositoryByData = despesasRepository.findByData(ano, mes);
        List<RelatorioCategoriaVo> relatorioCategoriaVos = despesasRepository.relatorioDeCategorias(ano, mes);
        Resumo resumo = Resumo.converter(receitasRepositoryByData,despesasRepositoryByData,relatorioCategoriaVos);
        return resumo;
    }
}
