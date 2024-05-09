package com.junio.sistemafinanceiro.repositories;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
