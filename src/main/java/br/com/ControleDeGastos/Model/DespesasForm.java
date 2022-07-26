package br.com.ControleDeGastos.Model;

import br.com.ControleDeGastos.repository.DespesasRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DespesasForm {
	@NotNull@NotEmpty
	private String descricao;
	@NotNull@NotEmpty
	private String valor;
	@NotNull@NotEmpty
	private String data;
	@NotNull
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Despesas converter() {
		Despesas despesas = new Despesas();
		despesas.setData(LocalDate.parse(this.data));
		despesas.setDescricao(this.descricao);
		despesas.setValor(Float.parseFloat(this.valor));
		despesas.setCategoria(this.categoria);
		return despesas;
	}


	public Despesas atualiza(Long id, DespesasRepository repository) {
		Despesas despesa = repository.findById(id).get();
		despesa.setData(LocalDate.parse(this.data));
		despesa.setDescricao(this.descricao);
		despesa.setValor(Float.parseFloat(this.valor));
		despesa.setCategoria(this.categoria);
		repository.save(despesa);
		return despesa;
	}
}
