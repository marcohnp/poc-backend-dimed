package com.dimed.backend.exception.exceptions;

import org.springframework.http.HttpStatus;

public class LinhasOnibusNotFound extends RuntimeException {

    public LinhasOnibusNotFound(){
        super("Linhas de Onibus não encontradas.");
    }
}
