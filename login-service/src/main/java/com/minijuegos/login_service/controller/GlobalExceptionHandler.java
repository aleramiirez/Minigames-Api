package com.minijuegos.login_service.controller;

import com.minijuegos.login_service.exceptions.EmptyFieldException;
import com.minijuegos.login_service.exceptions.InvalidCredentialsException;
import com.minijuegos.login_service.exceptions.UserNotFoundException;
import com.minijuegos.login_service.exceptions.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja UsernameAlreadyExistsException y devuelve una respuesta 409 Conflict.
     *
     * @param ex la UsernameAlreadyExistsException
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Maneja InvalidCredentialsException y devuelve una respuesta 401 Unauthorized.
     *
     * @param ex la InvalidCredentialsException
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Maneja EmptyFieldException y devuelve una respuesta 400 Bad Request.
     *
     * @param ex la EmptyFieldException
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Map<String, String>> handleEmptyFieldException(EmptyFieldException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones generales y devuelve una respuesta 500 Internal Server Error.
     *
     * @param ex la excepción general
     * @return un ResponseEntity con el mensaje de error y el estado HTTP
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
