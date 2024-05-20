package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.entidades.categoria.DadosCadastroCategoria;
import com.junio.sistemafinanceiro.repositories.CategoriaRepository;
import com.junio.sistemafinanceiro.entidades.categoria.DadosAtualizarCategoria;
import com.junio.sistemafinanceiro.service.exceptions.CategoriaJaExistenteException;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Create
    public Categoria createCategoria(DadosCadastroCategoria dados) {
        String nome = dados.nome();

        if (categoriaRepository.existsByNome(nome)) {
            throw new CategoriaJaExistenteException();
        }

        Categoria categoria = new Categoria(dados);
        return categoriaRepository.save(categoria);
    }

    // Read
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAllByAtivoIsTrue();
    }

    public Categoria findCategoriaById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findByIdAndAtivoIsTrue(id);
        return categoria.orElseThrow();
    }


    // Update
    public Categoria updateCategoria(Long id, DadosAtualizarCategoria dados) {
        Categoria categoria = findCategoriaById(id);

        if (StringUtils.isNotBlank(dados.nome())) {
            categoria.setNome(dados.nome());
        } else if (dados.nome() != null){
            throw new DadoInvalidoException("Nome não pode ser vazio.");
        }

        return categoriaRepository.save(categoria);
    }

    // Delete
    public void deleteLogicoCategoria(Long id) {
        Categoria categoria = findCategoriaById(id);
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }
}
