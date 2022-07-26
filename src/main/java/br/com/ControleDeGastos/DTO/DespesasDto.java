package br.com.ControleDeGastos.DTO;

import br.com.ControleDeGastos.Model.Categoria;
import br.com.ControleDeGastos.Model.Despesas;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DespesasDto {
	@NotNull@NotEmpty
	private Long id;
	@NotNull@NotEmpty
	private String descricao;
	@NotNull@NotEmpty
	private float valor;
	@NotNull@NotEmpty
	private LocalDate data;
	@NotNull
	private Categoria categoria;
	
	
	
	public DespesasDto(Despesas despesas) {
		this.id = despesas.getId();
		this.descricao = despesas.getDescricao();
		this.valor = despesas.getValor();
		this.data = despesas.getData();
		this.categoria = despesas.getCategoria();
	}


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
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static List<DespesasDto> converter(List<Despesas> despesas) {
		
		return despesas.stream().map(DespesasDto::new).collect(Collectors.toList());
	}
	
	
}
