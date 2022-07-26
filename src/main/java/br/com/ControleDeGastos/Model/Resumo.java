package br.com.ControleDeGastos.Model;

import br.com.ControleDeGastos.Vo.RelatorioCategoriaVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resumo {
    private float receitas;
    private float despesas;
    private float saldoFinal;
    private Map<Categoria,Double> totalGastoPorCategoria = new HashMap<>();

    public static Resumo converter(List<Receitas> receitasPorData, List<Despesas> despesasPorData, List<RelatorioCategoriaVo> relatorioCategoriaVos) {
        Resumo resumo = new Resumo();
        resumo.setReceitas(receitaFinal(receitasPorData));
        resumo.setDespesas(despesasFinal(despesasPorData));
        resumo.setSaldoFinal(receitaFinal(receitasPorData) - despesasFinal(despesasPorData));
        resumo.setTotalGastoPorCategoria(despesasPorCategoria(relatorioCategoriaVos));
        return resumo;
    }

    private static Map<Categoria, Double> despesasPorCategoria(List<RelatorioCategoriaVo> relatorioCategoriaVos) {
        Map<Categoria,Double> DicionarioDeCategorias = new HashMap<>();
        for (RelatorioCategoriaVo relatorio: relatorioCategoriaVos) {
            DicionarioDeCategorias.put(relatorio.getCategoria(),relatorio.getValor());
        }
        return DicionarioDeCategorias;
    }


    private static float despesasFinal(List<Despesas> despesasPorData) {
        float valorFinal = 0;
        for (Despesas despesa: despesasPorData) {
            valorFinal += despesa.getValor();
        }
        return valorFinal;
    }
    private static float receitaFinal(List<Receitas> receitasPorData){
        float valorFinal = 0;
        for (Receitas receita : receitasPorData){
            valorFinal += receita.getValor();
        }
        return valorFinal;

    }

    public float getReceitas() {
        return receitas;
    }

    public void setReceitas(float receitas) {
        this.receitas = receitas;
    }

    public float getDespesas() {
        return despesas;
    }

    public void setDespesas(float despesas) {
        this.despesas = despesas;
    }

    public float getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(float saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Map<Categoria, Double> getTotalGastoPorCategoria() {
        return totalGastoPorCategoria;
    }

    public void setTotalGastoPorCategoria(Map<Categoria, Double> totalGastoPorCategoria) {
        this.totalGastoPorCategoria = totalGastoPorCategoria;
    }

}
