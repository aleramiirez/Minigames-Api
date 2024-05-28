package com.minijuegos.login_service.exceptions;

/**
 * Excepción lanzada cuando un nombre de usuario ya existe en la base de datos.
 */
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
