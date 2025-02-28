package com.rentsclients.rentsandclients.Exceptions;
//ou ClientNifAlreadyExistsException
public class DuplicateClientNifException extends RuntimeException {
    public DuplicateClientNifException(String message) {
        super(message);
    }
}
