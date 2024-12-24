package com.LiterAlura.demo.Exceptions;

public class LibroNoEncontradoException extends RuntimeException{
    public LibroNoEncontradoException(String message) {
        super(message);
    }
}
