package com.minijuegos.userfunctions.exceptions;

/**
 * Excepción lanzada cuando un campo requerido está vacío o en blanco.
 */
public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super(message);
    }
}