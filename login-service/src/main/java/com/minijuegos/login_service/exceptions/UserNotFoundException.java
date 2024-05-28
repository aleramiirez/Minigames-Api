package com.minijuegos.login_service.exceptions;

/**
 * Excepción lanzada cuando un usuario no se encuentra en la base de datos.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
