package com.food.ordering.system.application.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception e) {
        log.error("An exception occurred: {}", e.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error occurred")
                .build();
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ValidationException e) {

        ErrorDTO errorDTO;
        if (e instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) e);
            log.error(violations, e);
            errorDTO = ErrorDTO.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            String exceptionMessage = e.getMessage();
            log.error(exceptionMessage, e);
            errorDTO = ErrorDTO.builder()
                    .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }
        return errorDTO;
    }

    private String extractViolationsFromException(ConstraintViolationException e) {
        return e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

}
