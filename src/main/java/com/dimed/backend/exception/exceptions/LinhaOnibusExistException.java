package com.dimed.backend.exception.exceptions;

public class LinhaOnibusExistException extends RuntimeException {

    public LinhaOnibusExistException(){
        super("Linha de Onibus já existe.");
    }
}
