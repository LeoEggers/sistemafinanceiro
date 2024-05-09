package com.junio.sistemafinanceiro.enums;

public enum TipoLancamento {

    RECEITA(1),
    DESPESA(2);

    private final int code;

    private TipoLancamento(int code) {
        this.code = code;
    }

    private int getCode() {
        return code;
    }

    private static TipoLancamento getTipo(int code) {
        for (TipoLancamento tipo : TipoLancamento.values()) {
            if (tipo.getCode() == code) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Não há lançamento cadastrado com o valor " + code);
    }
}
