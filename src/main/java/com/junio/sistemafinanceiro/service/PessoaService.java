package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.pessoa.DadosAtualizarPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosCadastroPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import com.junio.sistemafinanceiro.repositories.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    // Create
    public Pessoa createPessoa(DadosCadastroPessoa dados) {
        var pessoa = new Pessoa(dados);
        return pessoaRepository.save(pessoa);
    }

    // Read
    public List<Pessoa> findAllPessoas() {
        return pessoaRepository.findAllByAtivoIsTrue();
    }

    public Pessoa findPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findByIdAndAtivoIsTrue(id);
        return pessoa.orElseThrow();
    }

    // Update
    public Pessoa updatePessoa(Long id, DadosAtualizarPessoa dados) {
        var pessoa = findPessoaById(id);
        if (dados.nome() != null) {
            pessoa.setNome(dados.nome());
        }
        if (dados.endereco() != null) {
            pessoa.setEndereco(dados.endereco());
        }
        return pessoaRepository.save(pessoa);
    }

    // Delete
    public void deleteLogicoPessoa(Long id) {
        Pessoa pessoa = findPessoaById(id);
        pessoa.setAtivo(false);
        pessoaRepository.save(pessoa);
    }
}
