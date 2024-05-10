package com.junio.sistemafinanceiro.entidades.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastroCategoria(
        @NotBlank
        @Size(min = 2, max = 20)
        String nome
) {
}
