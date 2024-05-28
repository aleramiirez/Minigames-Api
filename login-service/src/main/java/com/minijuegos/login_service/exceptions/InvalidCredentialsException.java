package com.minijuegos.login_service.exceptions;

/**
 * Excepción lanzada cuando se proporcionan credenciales inválidas.
 */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}