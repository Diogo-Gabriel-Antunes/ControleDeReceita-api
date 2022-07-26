package br.com.ControleDeGastos.DTO;

import br.com.ControleDeGastos.Model.Receitas;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReceitasDto {
	@NotNull@NotEmpty
	private Long id;
	@NotNull@NotEmpty
	private String descricao;
	@NotNull@NotEmpty
	private float valor;
	@NotNull@NotEmpty
	private LocalDate data;



	public ReceitasDto(Receitas receitas) {
		this.id = receitas.getId();
		this.descricao = receitas.getDescricao();
		this.valor = receitas.getValor();
		this.data = receitas.getData();
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
	public static List<ReceitasDto> converter(List<Receitas> receitas) {
		
		return receitas.stream().map(ReceitasDto::new).collect(Collectors.toList());
	}
	
	
}
