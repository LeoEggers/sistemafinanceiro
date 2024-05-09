package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
