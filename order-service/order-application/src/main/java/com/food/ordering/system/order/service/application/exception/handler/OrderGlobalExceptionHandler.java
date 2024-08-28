package com.food.ordering.system.order.service.application.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.food.ordering.system.application.handler.ErrorDTO;
import com.food.ordering.system.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OrderDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(OrderDomainException e) {
        log.error("An exception occurred: {}", e.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(OrderNotFoundException e) {
        log.error("An exception occurred: {}", e.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

}
