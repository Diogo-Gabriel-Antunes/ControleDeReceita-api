package br.com.ControleDeGastos.controller;

import br.com.ControleDeGastos.DTO.ReceitasDto;
import br.com.ControleDeGastos.Model.Receitas;
import br.com.ControleDeGastos.Model.ReceitasForm;
import br.com.ControleDeGastos.repository.ReceitasRepository;
import br.com.ControleDeGastos.service.ReceitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository repository;


    @PostMapping
    public ResponseEntity<ReceitasDto> cadastrar(@RequestBody @Valid ReceitasForm receitasForm, UriComponentsBuilder uriBuilder){
        if(!ReceitasService.validandoDescricao(repository,receitasForm)){
            Receitas receitas = receitasForm.converter();
            repository.save(receitas);

            URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receitas.getId()).toUri();
            return ResponseEntity.created(uri).body(new ReceitasDto(receitas));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<ReceitasDto> listar(String descricao){
        if(descricao == null){
            List<Receitas> receitas = repository.findAll();
            List<ReceitasDto> receitasDtos = ReceitasDto.converter(receitas);

            return receitasDtos;
        }else{
           List<Receitas> receitas = repository.findBydescricaoContainingIgnoreCase(descricao);
           List<ReceitasDto> receitasDtos = ReceitasDto.converter(receitas);

           return receitasDtos;
        }


    }

    @GetMapping("/{id}")
    public ReceitasDto detalhar(@PathVariable long id){
        Optional<Receitas> receita = repository.findById(id);

        return new ReceitasDto(receita.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitasDto> atualizar(@PathVariable long id,@RequestBody ReceitasForm receitasForm){
        if(!ReceitasService.validandoDescricao(repository,receitasForm)){
            Receitas receita = receitasForm.atualiza(id, repository);
            return ResponseEntity.ok(new ReceitasDto(receita));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        Optional<Receitas> receita = repository.findById(id);
        if(receita.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }

    }

    @GetMapping("/{ano}/{mes}")
    public List<ReceitasDto> listarPorMes(@PathVariable int ano,@PathVariable int mes){
        List<Receitas> receitas = repository.findByData(ano, mes);
        List<ReceitasDto> receitasDtos = ReceitasDto.converter(receitas);

        return receitasDtos;
    }
}
