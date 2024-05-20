package com.junio.sistemafinanceiro.service;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.Endereco;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosAtualizarPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.DadosCadastroPessoa;
import com.junio.sistemafinanceiro.entidades.pessoa.Pessoa;
import com.junio.sistemafinanceiro.repositories.PessoaRepository;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

        // atualizando Nome
        if (StringUtils.isNotBlank(dados.nome())) {
            if (dados.nome().length() < 3 || dados.nome().length() > 100) {
                throw new DadoInvalidoException("Nome deve ter entre 3 e 100 caracteres");
            }
            pessoa.setNome(dados.nome());
        } else if (dados.nome() != null) {
            throw new DadoInvalidoException("Nome não pode ser vazio");
        }

        Endereco endereco = pessoa.getEndereco();

        if (dados.endereco() != null) {

            // atualizando Logradouro
            if (StringUtils.isNotBlank(dados.endereco().getLogradouro())) {
                endereco.setLogradouro(dados.endereco().getLogradouro());
            } else if (dados.endereco().getLogradouro() != null) {
                throw new DadoInvalidoException("Logradouro não pode ser vazio");
            }

            // atualizando Bairro
            if (StringUtils.isNotBlank(dados.endereco().getBairro())) {
                endereco.setBairro(dados.endereco().getBairro());
            } else if (dados.endereco().getBairro() != null) {
                throw new DadoInvalidoException("Bairro não pode ser vazio");
            }

            // atualizando CEP
            if (StringUtils.isNotBlank(dados.endereco().getCep())) {
                if (!dados.endereco().getCep().matches("^[0-9]{8}$")) {
                    throw new DadoInvalidoException("CEP inválido.");
                }
                endereco.setCep(dados.endereco().getCep());
            } else if (dados.endereco().getCep() != null) {
                throw new DadoInvalidoException("CEP não pode ser vazio");
            }

            // atualizando Número
            if (StringUtils.isNotBlank(dados.endereco().getNumero())) {
                endereco.setNumero(dados.endereco().getNumero());
            } else if (dados.endereco().getNumero() != null) {
                throw new DadoInvalidoException("Número não pode ser vazio");
            }

            // atualizando Complemento
            if (StringUtils.isNotBlank(dados.endereco().getComplemento())) {
                endereco.setComplemento(dados.endereco().getComplemento());
            } else if (dados.endereco().getComplemento() != null) {
                throw new DadoInvalidoException("Complemento não pode ser vazio");
            }

            // atualizando Cidade
            if (StringUtils.isNotBlank(dados.endereco().getCidade())) {
                endereco.setCidade(dados.endereco().getCidade());
            } else if (dados.endereco().getCidade() != null) {
                throw new DadoInvalidoException("Cidade não pode ser vazio");
            }

            // atualizando UF
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
        pessoaRepository.save(pessoa);
    }
}
