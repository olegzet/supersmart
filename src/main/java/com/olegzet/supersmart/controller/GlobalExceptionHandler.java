package com.olegzet.supersmart.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.olegzet.supersmart.exception.ValidationAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    ServletContext servletContext;

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(MethodArgumentNotValidException exception) {
        return makeErrorResponse(
                exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .findFirst().orElse("MethodArgumentNotValidException, but an error message was lost."));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ConstraintViolationException exception) {
        return makeErrorResponse(
                exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                        .findFirst().orElse("ConstraintViolationException, but an error message was lost."));
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(ValidationAppException exception) {
        return makeErrorResponse(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(MismatchedInputException exception) {
        return makeErrorResponse(
                "JSON input payload can't be parsed. Reason: " + exception.getMessage());
    }

    private ResponseError makeErrorResponse(String message) {
        return new ResponseError(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message, servletContext.getContextPath());
    }
}
