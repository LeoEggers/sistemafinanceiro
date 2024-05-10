package com.junio.sistemafinanceiro.entidades.lancamento;

import com.junio.sistemafinanceiro.entidades.categoria.DadosCadastroCategoria;
import com.junio.sistemafinanceiro.entidades.lancamento.enums.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLancamento(
        @NotBlank
        String descricao,
        String observacao,
        @NotNull
        Double valor,
        @NotNull
        TipoLancamento tipoLancamento,
        @NotNull
        Long idPessoa,
        Long idCategoria,
        DadosCadastroCategoria categoria
) {
}
