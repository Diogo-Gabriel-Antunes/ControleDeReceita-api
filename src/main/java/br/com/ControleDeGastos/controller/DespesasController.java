package br.com.ControleDeGastos.controller;

import br.com.ControleDeGastos.DTO.DespesasDto;
import br.com.ControleDeGastos.Model.Despesas;
import br.com.ControleDeGastos.Model.DespesasForm;
import br.com.ControleDeGastos.repository.DespesasRepository;
import br.com.ControleDeGastos.service.DespesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static br.com.ControleDeGastos.service.DespesasService.validandoDescricao;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
	
	@Autowired
	private DespesasRepository despesasRepository;
	@Autowired
	private DespesasService service;
	
	@GetMapping
	public List<DespesasDto> listar(String descricao){
		if(descricao == null){
			List<Despesas> despesas = despesasRepository.findAll();
			List<DespesasDto> despesasDtos = DespesasDto.converter(despesas);

			return despesasDtos;
		}else{
			List<Despesas> despesas = despesasRepository.findBydescricaoContainingIgnoreCase(descricao);
			List<DespesasDto> despesasDtos = DespesasDto.converter(despesas);

			return despesasDtos;
		}

	}
	
	@PostMapping
	public ResponseEntity<DespesasDto> cadastrar(@RequestBody @Valid DespesasForm despesasForm, UriComponentsBuilder uriBuilder) {
		if(validandoDescricao(despesasRepository,despesasForm)){
			Despesas despesas = despesasForm.converter();
			despesasRepository.save(despesas);

			URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesas.getId()).toUri();
			return ResponseEntity.created(uri).body(new DespesasDto(despesas));

		}else{
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<DespesasDto> detalhar(@PathVariable Long id){
		Optional<Despesas> despesas = despesasRepository.findById(id);
		if(despesas.isPresent()){
			return  ResponseEntity.ok(new DespesasDto(despesas.get()));
		}
		return ResponseEntity.badRequest().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity remover(@PathVariable Long id){
		Optional<Despesas> despesas = despesasRepository.findById(id);
		if(despesas.isEmpty()){
			return ResponseEntity.badRequest().build();
		}else{
			despesasRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

	}
	@PutMapping("/{id}")
	public ResponseEntity<DespesasDto> atualiza(@PathVariable Long id,@RequestBody @Valid DespesasForm despesasForm){
		if(service.validandoDescricao(despesasRepository, despesasForm)){
			Despesas despesas = despesasForm.atualiza(id, despesasRepository);
			return ResponseEntity.ok(new DespesasDto(despesas));
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{ano}/{mes}")
	public List<DespesasDto> listarPorMes(@PathVariable int ano, @PathVariable int mes){
		List<Despesas> despesas = despesasRepository.findByData(ano, mes);
		List<DespesasDto> despesasDto = DespesasDto.converter(despesas);

		return despesasDto;
	}
}
