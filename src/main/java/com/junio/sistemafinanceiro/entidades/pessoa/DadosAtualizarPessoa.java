package com.junio.sistemafinanceiro.entidades.pessoa;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizarPessoa(
        String nome,
        String logradouro,
        String bairro,
        @Pattern(regexp = "^[0-9]{8}$")
        String cep,
        String cidade,
        @Pattern(regexp = "[A-Z]{2}")
        String uf,
        String complemento,
        String numero) {
}
