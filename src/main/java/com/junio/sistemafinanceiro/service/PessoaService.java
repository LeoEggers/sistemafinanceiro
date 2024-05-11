package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.endereco.Endereco;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosAtualizarPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosCadastroPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import com.junio.sistemafinanceiro.repositories.PessoaRepository;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
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
        return pessoaRepository.findAllByAtivoIsTrue();
    }

    public Pessoa findPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findByIdAndAtivoIsTrue(id);
        return pessoa.orElseThrow();
    }

    // Update
    public Pessoa updatePessoa(Long id, DadosAtualizarPessoa dados) {

        System.out.println("Entrou no método atualizar");
        var pessoa = findPessoaById(id);

        if (dados.nome() != null) {
            pessoa.setNome(dados.nome());
        }

        Endereco endereco = pessoa.getEndereco();

        if (dados.logradouro() != null) {
            if (!dados.logradouro().isBlank()) {
                endereco.setLogradouro(dados.logradouro());
                System.out.println(dados.logradouro());
                System.out.println("ta dizendo que nãp é blank");
            } else {
                System.out.println("ta dizendo que ta branco");
                throw new DadoInvalidoException(dados.logradouro());
            }
        }

        if (dados.bairro() != null) {
            endereco.setBairro(dados.bairro());
        }

        if (dados.cep() != null) {
            endereco.setCep(dados.cep());
        }

        if (dados.numero() != null) {
            endereco.setNumero(dados.numero());
        }

        if (dados.complemento() != null) {
            endereco.setComplemento(dados.complemento());
        }

        if (dados.cidade() != null) {
            endereco.setCidade(dados.cidade());
        }

        if (dados.uf() != null) {
            endereco.setUf(dados.uf());
        }

        pessoa.setEndereco(endereco);

        return pessoaRepository.save(pessoa);
    }

    // Delete
    public void deleteLogicoPessoa(Long id) {
        Pessoa pessoa = findPessoaById(id);
        pessoa.setAtivo(false);
        pessoaRepository.save(pessoa);
    }
}
