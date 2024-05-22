package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByAtivoTrue();

    Optional<Categoria> findAtivoById(Long id);

    boolean existsByNome(String nome);
}
