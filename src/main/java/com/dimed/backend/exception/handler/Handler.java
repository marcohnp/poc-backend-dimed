package com.dimed.backend.exception.handler;

import com.dimed.backend.exception.error.StandardError;
import com.dimed.backend.exception.exceptions.LinhasOnibusNotFoundException;
import com.dimed.backend.exception.exceptions.LinhaOnibusExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;


@ControllerAdvice
public class Handler {

    @ExceptionHandler(LinhasOnibusNotFoundException.class)
    public ResponseEntity<StandardError> linhasOnibusNotFound(LinhasOnibusNotFoundException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LinhaOnibusExistException.class)
    public ResponseEntity<StandardError> linhasOnibusNotCreated(LinhaOnibusExistException e, HttpServletRequest request) {
        return new ResponseEntity<>(
                StandardError.builder()
                        .timestamp(Instant.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Request not completed.")
                        .message(e.getMessage())
                        .path(request.getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }

}
