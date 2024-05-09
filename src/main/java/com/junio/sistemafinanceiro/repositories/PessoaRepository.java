package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
