package com.junio.sistemafinanceiro.service.exceptions;

public class DadoInvalidoException extends RuntimeException{

    public DadoInvalidoException(Object o){
        super("Dado inválido: " + o.toString());
    }
}
