package br.com.ControleDeGastos.repository;

import br.com.ControleDeGastos.Model.Despesas;
import br.com.ControleDeGastos.Vo.RelatorioCategoriaVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {

    @Query("SELECT D FROM Despesas D WHERE MONTH(data) = :month AND YEAR(data) = :year")
    List<Despesas> findByData(int year, int month);

    List<Despesas> findBydescricaoContainingIgnoreCase(String descricao);

    @Query("SELECT new br.com.ControleDeGastos.Vo.RelatorioCategoriaVo(d.categoria, d.data, SUM(d.valor)) FROM Despesas d WHERE MONTH(data) = :month AND YEAR(data) = :year GROUP BY categoria")
    List<RelatorioCategoriaVo> relatorioDeCategorias(int year, int month);
}
