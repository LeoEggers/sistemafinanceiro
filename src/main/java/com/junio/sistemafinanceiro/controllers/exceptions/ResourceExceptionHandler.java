package com.junio.sistemafinanceiro.controllers.exceptions;

import com.junio.sistemafinanceiro.service.exceptions.DatabaseException;
import com.junio.sistemafinanceiro.service.exceptions.ResourceNotFoundException;
import com.junio.sistemafinanceiro.service.exceptions.DadoInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class ResourceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String mensagem = "Internal server error";

        if (e instanceof ResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            mensagem = "Recurso não encontrado";

        } else if (e instanceof DatabaseException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Violação da Integridade do Banco de Dados";

        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Violação de restrição de integridade do banco de dados";

        } else if (e instanceof DataIntegrityViolationException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Violação de integridade de dados";

        } else if (e instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Dados inconsistentes com o formato ou tamanho permitido para a coluna";

        } else if (e instanceof DadoInvalidoException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Dados inválidos fornecidos";

        } else if (e instanceof UnexpectedTypeException) {
            status = HttpStatus.BAD_REQUEST;
            mensagem = "Tipo de dado inesperado fornecido";
        }

        // Logar o stack trace completo para fins de depuração
        logger.error("Error: ", e);

        StandardError error = new StandardError(Instant.now(),
                status.value(),
                mensagem, // mensagem de erro amigável
                request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
