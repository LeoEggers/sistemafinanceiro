package com.junio.sistemafinanceiro.entidades.lancamento;

import com.junio.sistemafinanceiro.entidades.lancamento.annotations.ValorPadrao;
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
        @NotNull
        Long idCategoria,
        @ValorPadrao(30)
        Integer diasParaOVencimento,
        Boolean transacaoConcluida
) {
}
