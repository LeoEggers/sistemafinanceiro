package com.junio.sistemafinanceiro.controllers;

import com.junio.sistemafinanceiro.entidades.lancamento.DadosAtualizarLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.DadosCadastroLancamento;
import com.junio.sistemafinanceiro.entidades.lancamento.Lancamento;
import com.junio.sistemafinanceiro.service.LancamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("Lancamentos")
@AllArgsConstructor
public class ControllerLancamento {

    private final LancamentoService lancamentoService;

    @PostMapping
    public ResponseEntity<Lancamento> cadastrarLancamento(@RequestBody @Valid DadosCadastroLancamento dados) {
        Lancamento lancamento = lancamentoService.createLancamento(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).body(lancamento);
    }

    @GetMapping
    public ResponseEntity<List<Lancamento>> findAllLancamentos() {
        List<Lancamento> lancamentos = lancamentoService.findAllLancamentos();
        return ResponseEntity.ok().body(lancamentos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lancamento> findLancamentoById(@PathVariable Long id) {
        Lancamento lancamento = lancamentoService.findLancamentoById(id);
        return ResponseEntity.ok().body(lancamento);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id,
                                                       @RequestBody @Valid DadosAtualizarLancamento dados) {
        Lancamento lancamento = lancamentoService.updateLancamento(id, dados);
        return ResponseEntity.ok().body(lancamento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
        lancamentoService.deleteLogicoLancamento(id);
        return ResponseEntity.noContent().build();
    }
}
