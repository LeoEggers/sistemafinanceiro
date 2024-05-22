package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.Endereco;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import org.apache.commons.lang3.StringUtils;

public record DadosAtualizarPessoa(
        String nome,
        Endereco endereco) {

    public DadosAtualizarPessoa {
        // Validação do nome
        if (nome != null) {
            if (StringUtils.isNotBlank(nome)) {
                if (nome.length() < 3 || nome.length() > 100) {
                    throw new DadoInvalidoException("Nome deve ter entre 3 e 100 caracteres");
                }
            } else {
                throw new DadoInvalidoException("Nome não pode ser vazio");
            }
        }

        // Validação do endereço
        if (endereco != null) {
            if (StringUtils.isNotBlank(endereco.getLogradouro())) {
                if (endereco.getLogradouro().length() < 3 || endereco.getLogradouro().length() > 100) {
                    throw new DadoInvalidoException("Logradouro deve ter entre 3 e 100 caracteres");
                }
            } else if (endereco.getLogradouro() != null) {
                throw new DadoInvalidoException("Logradouro não pode ser vazio");
            }

            if (StringUtils.isNotBlank(endereco.getBairro())) {
                if (endereco.getBairro().length() < 3 || endereco.getBairro().length() > 100) {
                    throw new DadoInvalidoException("Bairro deve ter entre 3 e 100 caracteres");
                }
            } else if (endereco.getBairro() != null) {
                throw new DadoInvalidoException("Bairro não pode ser vazio");
            }

            if (StringUtils.isNotBlank(endereco.getCep())) {
                if (!endereco.getCep().matches("^[0-9]{8}$")) {
                    throw new DadoInvalidoException("CEP inválido.");
                }
            } else if (endereco.getCep() != null) {
                throw new DadoInvalidoException("CEP não pode ser vazio");
            }

            if (StringUtils.isNotBlank(endereco.getNumero())) {
                if (endereco.getNumero().length() > 20) {
                    throw new DadoInvalidoException("Número deve no máximo 20 caracteres");
                }
            } else if (endereco.getNumero() != null) {
                throw new DadoInvalidoException("Número não pode ser vazio");
            }

            if (StringUtils.isNotBlank(endereco.getComplemento())) {
                if (endereco.getComplemento().length() > 100) {
                    throw new DadoInvalidoException("Complemento deve no máximo 100 caracteres");
                }
            } else if (endereco.getComplemento() != null) {
                throw new DadoInvalidoException("Complemento não pode ser vazio");
            }

            if (StringUtils.isNotBlank(endereco.getCidade())) {
                if (endereco.getCidade().length() < 3 || endereco.getCidade().length() > 100) {
                    throw new DadoInvalidoException("Cidade deve ter entre 3 e 100 caracteres");
                }
            } else if (endereco.getCidade() != null) {
                throw new DadoInvalidoException("Cidade não pode ser vazio");
            }
        }
    }
}
