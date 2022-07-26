package br.com.ControleDeGastos.repository;

import br.com.ControleDeGastos.Model.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas,Long> {
    @Query("SELECT R FROM Receitas R WHERE MONTH(data) = :month AND YEAR(data) = :year")
    List<Receitas> findByData(int year, int month);

    List<Receitas> findBydescricaoContainingIgnoreCase(String descricao);
}
