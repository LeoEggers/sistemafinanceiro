package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.pessoa.endereco.Endereco;

public record DadosAtualizarPessoa(
        String nome,
        Endereco endereco) {
}
