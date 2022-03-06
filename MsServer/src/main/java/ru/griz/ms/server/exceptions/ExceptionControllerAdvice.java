package ru.griz.ms.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> HandleResourceNotFoundExceptions(ResourceNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionInfoDTO(HttpStatus.NOT_FOUND.value(),
                        e.getClass() + ": " + e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<?> HandleOtherExceptions(Exception e) {
        return new ResponseEntity<>(
                new ExceptionInfoDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getClass() + ": " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
