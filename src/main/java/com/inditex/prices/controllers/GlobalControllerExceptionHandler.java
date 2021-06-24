package com.inditex.prices.controllers;

import com.inditex.prices.dtos.ErrorDTO;
import com.inditex.prices.exceptions.DatePriceFormatException;
import com.inditex.prices.exceptions.PriceNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author Eze Q.
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleNoParamPresent(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleConnversion(MethodArgumentTypeMismatchException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PriceNotFoudException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleNotFound(PriceNotFoudException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatePriceFormatException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorDTO> handleDatePriceFormat(DatePriceFormatException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleBookNotFound(Exception ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
