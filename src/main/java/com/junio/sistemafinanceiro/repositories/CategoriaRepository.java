package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByAtivoIsTrue();

    Optional<Categoria> findByIdAndAtivoIsTrue(Long id);

    boolean existsByNome(String nome);
}
