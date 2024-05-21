package com.junio.sistemafinanceiro.entidades.categoria;

import jakarta.validation.constraints.Size;

public record DadosAtualizarCategoria(
        @Size(min = 1, max = 20)
        String nome
) {
}
