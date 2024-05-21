package com.junio.sistemafinanceiro.entidades.lancamento;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;

import java.time.Instant;

public record DadosAtualizarLancamento(
        String descricao,
        String observacao,
        Double valor,
        Instant dataLancamento,
        Instant dataVencimento,
        Instant dataConclusao,
        Categoria categoria,
        Boolean transacaoConcluida
) {
}
