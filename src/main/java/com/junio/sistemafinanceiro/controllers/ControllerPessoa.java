package com.junio.sistemafinanceiro.controllers;

import com.junio.sistemafinanceiro.entidades.pessoa.DadosAtualizarPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosCadastroPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import com.junio.sistemafinanceiro.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("Pessoas")
@AllArgsConstructor
public class ControllerPessoa {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody @Valid DadosCadastroPessoa dados) {
        var pessoa = pessoaService.createPessoa(dados);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas() {
        List<Pessoa> pessoas = pessoaService.findAllPessoas();
        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id,
                                               @RequestBody @Valid DadosAtualizarPessoa dados) {
        Pessoa pessoa = pessoaService.updatePessoa(id, dados);
        return ResponseEntity.ok().body(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deleteLogicoPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
