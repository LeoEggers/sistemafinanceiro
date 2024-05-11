package com.junio.sistemafinanceiro.service.exceptions;

public class CategoriaJaExistenteException extends RuntimeException {

    public CategoriaJaExistenteException() {
        super("Categoria já existente.");
    }
}
