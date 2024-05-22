package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByAtivoTrue();

    Optional<Pessoa> findAtivoById(Long id);
}
