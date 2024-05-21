package com.junio.sistemafinanceiro.entidades.categoria;

import jakarta.validation.constraints.Size;

public record DadosCadastroCategoria(
        @Size(min = 2, max = 20)
        String nome
) {
}
