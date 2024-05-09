package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.endereco.Endereco;
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

    private PessoaRepository pessoaRepository;

    // Create
    public Pessoa createPessoa(DadosCadastroPessoa dados) {
        var pessoa = new Pessoa(dados);
        return pessoaRepository.save(pessoa);
    }

    // Read
    public List<Pessoa> findAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa findPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElseThrow();
    }

    // Update
    public Pessoa updatePessoa(Long id, DadosAtualizarPessoa dados) {
        var pessoa = findPessoaById(id);

        if (dados.nome() != null) {
            pessoa.setNome(dados.nome());
        }

        if (dados.endereco() != null) {
            Endereco endereco = new Endereco();

            if (dados.endereco().getLogradouro() != null) {
                endereco.setLogradouro(dados.endereco().getLogradouro());
            }

            if (dados.endereco().getBairro() != null) {
                endereco.setBairro(dados.endereco().getBairro());
            }

            if (dados.endereco().getCep() != null) {
                endereco.setCep(dados.endereco().getCep());
            }

            if (dados.endereco().getNumero() != null) {
                endereco.setNumero(dados.endereco().getNumero());
            }

            if (dados.endereco().getComplemento() != null) {
                endereco.setComplemento(dados.endereco().getComplemento());
            }

            if (dados.endereco().getCidade() != null) {
                endereco.setCidade(dados.endereco().getCidade());
            }

            if (dados.endereco().getUf() != null) {
                endereco.setUf(dados.endereco().getUf());
            }

            pessoa.setEndereco(endereco);
        }

        return pessoaRepository.save(pessoa);
    }

    // Delete
    public void deleteLogicoPessoa(Long id) {
        Pessoa pessoa = findPessoaById(id);
        pessoa.setAtivo(false);
    }
}
