package br.com.ControleDeGastos.Model;

import br.com.ControleDeGastos.repository.ReceitasRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReceitasForm {
	@NotNull@NotEmpty
	private String descricao;
	@NotNull@NotEmpty
	private String valor;
	@NotNull@NotEmpty
	private String data;
	
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
	public Receitas converter() {
		Receitas receitas = new Receitas();
		receitas.setData(LocalDate.parse(this.data));
		receitas.setDescricao(this.descricao);
		receitas.setValor(Float.parseFloat(this.valor));
		return receitas;
	}


	public Receitas atualiza(Long id, ReceitasRepository repository) {
		Receitas receitas = repository.findById(id).get();
		receitas.setData(LocalDate.parse(this.data));
		receitas.setDescricao(this.descricao);
		receitas.setValor(Float.parseFloat(this.valor));
		repository.save(receitas);
		return receitas;
	}
}
