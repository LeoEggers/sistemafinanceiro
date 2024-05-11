//package com.junio.sistemafinanceiro.controllers.exceptions;
//
//import com.junio.sistemafinanceiro.service.exceptions.DatabaseException;
//import com.junio.sistemafinanceiro.service.exceptions.ResourceNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.Instant;
//
//@ControllerAdvice
//public class ResourceExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<StandardError> handleException(Exception e, HttpServletRequest request) {
//
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//        String mensagemDeErro = "Internal server error";
//
//        if (e instanceof ResourceNotFoundException) {
//            status = HttpStatus.NOT_FOUND;
//            mensagemDeErro = "Recurso não encontrado";
//        } else if (e instanceof DatabaseException) {
//            status = HttpStatus.BAD_REQUEST;
//            mensagemDeErro = "Violação da Integridade do Banco de Dados";
//        }
//
//        StandardError error = new StandardError(Instant.now(),
//                status.value(),
//                mensagemDeErro,
//                e.getMessage(),
//                request.getRequestURI());
//
//        return ResponseEntity.status(status).body(error);
//    }
//}

// todo:
//  java.sql.SQLIntegrityConstraintViolationException: Column 'logradouro' cannot be null
//  com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column 'cep' at row 1
//  org.springframework.dao.DataIntegrityViolationException: could not execute statement [Data truncation: Data too long for column 'cep' at row 1]