package com.junio.sistemafinanceiro.controllers;

import com.junio.sistemafinanceiro.entidades.categoria.DadosAtualizarCategoria;
import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.service.CategoriaService;
import com.junio.sistemafinanceiro.entidades.categoria.DadosCadastroCategoria;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Categorias")
@AllArgsConstructor
public class ControllerCategoria {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody @Valid DadosCadastroCategoria dados) {
        var categoria = categoriaService.createCategoria(dados);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> findAllCategorias() {
        List<Categoria> categorias = categoriaService.findAllCategorias();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id,
                                                     @RequestBody @Valid DadosAtualizarCategoria dados) {
        Categoria categoria = categoriaService.updateCategoria(id, dados);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCategoria(@RequestParam Long id) {
        categoriaService.deleteLogicoCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
