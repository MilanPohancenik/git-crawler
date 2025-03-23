package com.pohancenik.gitcrawler.infrastructure.exception;

import java.time.Clock;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(Instant.now(Clock.systemDefaultZone()),
                        HttpStatus.NOT_IMPLEMENTED.value(),
                        HttpStatus.NOT_IMPLEMENTED.name(),
                        "Operation not supported"),
                HttpStatus.NOT_IMPLEMENTED);
    }

}
