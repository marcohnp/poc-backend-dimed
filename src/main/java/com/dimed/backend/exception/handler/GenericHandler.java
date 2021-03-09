package com.dimed.backend.exception.handler;

import com.dimed.backend.exception.error.StandardError;
import com.dimed.backend.exception.exceptions.LinhasOnibusNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@ControllerAdvice
public class GenericHandler {

    @ExceptionHandler(LinhasOnibusNotFound.class)
    public ResponseEntity<StandardError> linhasOnibusNotFound(LinhasOnibusNotFound e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Resources not found.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.NOT_FOUND);
    }

}
