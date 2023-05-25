package com.ues.bibliotecalibro.exceptions;

public class ModeloNotFoundException extends RuntimeException{
    public ModeloNotFoundException(String message) {
        super(message);
    }
}
