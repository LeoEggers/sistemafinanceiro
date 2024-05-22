package com.junio.sistemafinanceiro.entidades.lancamento;

import com.junio.sistemafinanceiro.entidades.categoria.Categoria;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import org.apache.commons.lang3.StringUtils;

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
    public DadosAtualizarLancamento {
        // Validação da descrição
        if (descricao != null) {
            if (StringUtils.isNotBlank(descricao)) {
                if (descricao.length() < 3 || descricao.length() > 100) {
                    throw new DadoInvalidoException("Descrição deve ter entre 3 e 100 caracteres");
                }
            } else {
                throw new DadoInvalidoException("Descrição não pode ser vazia");
            }
        }

        // Validação da observação
        if (observacao != null) {
            if (StringUtils.isNotBlank(observacao)) {
                if (observacao.length() > 255) {
                    throw new DadoInvalidoException("Observação deve ter no máximo 255 caracteres");
                }
            } else {
                throw new DadoInvalidoException("Observação não pode ser vazia");
            }
        }

        // Validação do valor
        if (valor != null && valor <= 0) {
            throw new DadoInvalidoException("Valor deve ser maior que zero");
        }

        // Validação das datas
        if (dataLancamento != null && dataVencimento != null && dataLancamento.isAfter(dataVencimento)) {
            throw new DadoInvalidoException("Data de lançamento não pode ser posterior à data de vencimento");
        }

        if (dataConclusao != null && (dataLancamento != null && dataConclusao.isBefore(dataLancamento))) {
            throw new DadoInvalidoException("Data de conclusão não pode ser anterior à data de lançamento");
        }
    }
}
