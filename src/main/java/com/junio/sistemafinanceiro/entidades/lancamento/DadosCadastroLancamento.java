package com.junio.sistemafinanceiro.entidades.lancamento;

import com.junio.sistemafinanceiro.entidades.lancamento.enums.TipoLancamento;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLancamento(
        @NotBlank
        String descricao,
        @Nullable
        String observacao,
        @NotNull
        Double valor,
        @NotNull
        TipoLancamento tipoLancamento,
        @NotNull
        Long idPessoa,
        @NotNull
        Long idCategoria,
        Integer diasParaOVencimento,
        Boolean transacaoConcluida
) {
    public DadosCadastroLancamento(@NotBlank
                                   String descricao, @Nullable
                                   String observacao, @NotNull
                                   Double valor, @NotNull
                                   TipoLancamento tipoLancamento, @NotNull
                                   Long idPessoa, @NotNull
                                   Long idCategoria, Integer diasParaOVencimento, Boolean transacaoConcluida) {

        this.diasParaOVencimento = (diasParaOVencimento != null) ? diasParaOVencimento : 30;
        this.transacaoConcluida = (transacaoConcluida != null) ? transacaoConcluida : false;

        this.descricao = descricao;
        this.observacao = observacao;
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
        this.idPessoa = idPessoa;
        this.idCategoria = idCategoria;
    }
}
