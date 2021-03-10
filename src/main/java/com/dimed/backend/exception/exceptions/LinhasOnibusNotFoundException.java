package com.dimed.backend.exception.exceptions;

import org.springframework.http.HttpStatus;

public class LinhasOnibusNotFoundException extends RuntimeException {

    public LinhasOnibusNotFoundException(){
        super("Linhas de Onibus não encontradas.");
    }
}
