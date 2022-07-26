package br.com.ControleDeGastos.Vo;

import br.com.ControleDeGastos.Model.Categoria;

import java.time.LocalDate;

public class RelatorioCategoriaVo {
    private Categoria categoria;
    private LocalDate data;
    private Double valor;

    public RelatorioCategoriaVo(Categoria categoria, LocalDate data, double valor) {
        this.categoria = categoria;
        this.data = data;
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getValor() {
        return valor;
    }
}
