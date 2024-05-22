package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.Endereco;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;

public record DadosAtualizarPessoa(
        String nome,
        Endereco endereco) {

    public DadosAtualizarPessoa {
        // Validação do nome
        if (nome != null && (nome.length() < 3 || nome.length() > 100)) {
            throw new DadoInvalidoException("Nome deve ter entre 3 e 100 caracteres");
        }

        // Validação do endereço
        if (endereco != null) {

            // Validação do logradouro
            if (endereco.getLogradouro() != null && (endereco.getLogradouro().length() < 3 || endereco.getLogradouro().length() > 100)) {
                throw new DadoInvalidoException("Logradouro deve ter entre 3 e 100 caracteres");
            }

            // Validação do bairro
            if (endereco.getBairro() != null && (endereco.getBairro().length() < 3 || endereco.getBairro().length() > 100)) {
                throw new DadoInvalidoException("Bairro deve ter entre 3 e 100 caracteres");
            }

            // Validação do CEP
            if (endereco.getCep() != null && !endereco.getCep().matches("^[0-9]{8}$")) {
                throw new DadoInvalidoException("CEP inválido.");
            }

            // Validação do número
            if (endereco.getNumero() != null && endereco.getNumero().length() > 20) {
                throw new DadoInvalidoException("Número deve no máximo 20 caracteres");
            }

            // Validação do complemento
            if (endereco.getComplemento() != null && endereco.getComplemento().length() > 100) {
                throw new DadoInvalidoException("Complemento deve no máximo 100 caracteres");
            }

            // Validação da cidade
            if (endereco.getCidade() != null && (endereco.getCidade().length() < 3 || endereco.getCidade().length() > 100)) {
                throw new DadoInvalidoException("Cidade deve ter entre 3 e 100 caracteres");
            }
        }
    }
}
