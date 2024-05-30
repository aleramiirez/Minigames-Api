package com.minijuegos.userfunctions.controller;

import com.minijuegos.userfunctions.exceptions.EmptyFieldException;
import com.minijuegos.userfunctions.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la aplicación.
 * Maneja excepciones personalizadas y proporciona respuestas HTTP apropiadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja UserNotFoundException y devuelve una respuesta 404 Not Found.
     *
     * @param ex la UserNotFoundException
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja EmptyFieldException y devuelve una respuesta 400 Bad Request.
     *
     * @param ex la EmptyFieldException
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones generales y devuelve una respuesta 500 Internal Server Error.
     *
     * @param ex la excepción general
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
