package com.junio.sistemafinanceiro.entidades.pessoa;

import com.junio.sistemafinanceiro.entidades.endereco.Endereco;
import jakarta.validation.constraints.Size;

public record DadosAtualizarPessoa(
        String nome,
        Endereco endereco) {
}
