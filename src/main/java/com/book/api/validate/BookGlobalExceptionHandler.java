package com.book.api.validate;

import com.book.api.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.StringJoiner;

import org.springframework.dao.EmptyResultDataAccessException;

@ControllerAdvice
public class BookGlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Response> handleConstraintViolationException(ConstraintViolationException cve) {
        StringJoiner join = new StringJoiner(",");
        cve.getConstraintViolations().forEach(x -> join.add(x.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(0, join.toString(), Collections.emptyList()));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Response> handleEmptyResultDataAccessException(EmptyResultDataAccessException er) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(0, er.getMessage(), Collections.emptyList()));
    }

    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity<Response> handleInvalidFormatException(InvalidFormatException ife)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(0, ife.getMessage(), Collections.emptyList()));
    }
}
