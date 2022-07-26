package br.com.ControleDeGastos.service;

import br.com.ControleDeGastos.Model.Despesas;
import br.com.ControleDeGastos.Model.DespesasForm;
import br.com.ControleDeGastos.repository.DespesasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesasService {
    public static boolean validandoDescricao(DespesasRepository despesasRepository, DespesasForm despesasForm) {
        LocalDate data = LocalDate.parse(despesasForm.getData());
        List<Despesas> despesasDaData = despesasRepository.findByData(data.getYear(), data.getMonth().getValue());
        for (Despesas despesa : despesasDaData){
            if(despesasForm.getDescricao().equals(despesa.getDescricao())){
                return false;

            }
        }
        return true;
    }
}
