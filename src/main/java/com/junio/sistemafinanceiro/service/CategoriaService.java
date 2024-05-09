package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.repositories.CategoriaRepository;
import com.junio.sistemafinanceiro.entidades.categoria.DadosAtualizarCategoria;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Create
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Read
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria findCategoriaById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow();
    }

    // Update
    public Categoria updateCategoria(Long id, DadosAtualizarCategoria dados) {
        Categoria categoria = findCategoriaById(id);

        if (dados.nome() != null) {
            categoria.setNome(dados.nome());
        }

        return categoriaRepository.save(categoria);
    }

    // Delete
    public void deleteLogicoCategoria(Long id) {
        Categoria categoria = findCategoriaById(id);
        categoria.setAtivo(false);
    }
}
