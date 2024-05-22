package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByAtivoTrue();

    Optional<Lancamento> findAtivoById(Long id);
}
