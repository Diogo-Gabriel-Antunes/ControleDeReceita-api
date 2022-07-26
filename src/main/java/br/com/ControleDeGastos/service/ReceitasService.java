package br.com.ControleDeGastos.service;

import br.com.ControleDeGastos.Model.Receitas;
import br.com.ControleDeGastos.Model.ReceitasForm;
import br.com.ControleDeGastos.repository.ReceitasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceitasService {
    public static boolean validandoDescricao(ReceitasRepository repository, ReceitasForm receitasForm) {
        LocalDate data = LocalDate.parse(receitasForm.getData());
        List<Receitas> receitasDaData = repository.findByData(data.getYear(), data.getMonth().getValue());
        for (Receitas receita : receitasDaData){
            if(receitasForm.getDescricao().equals(receita.getDescricao())){
                return true;

            }
        }
        return false;
    }
}
