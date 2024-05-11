package com.junio.sistemafinanceiro.entidades.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "^[0-9]{8}$")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        @Pattern(regexp = "[A-Z]{2}")
        String uf,
        String complemento,
        String numero) {
}
