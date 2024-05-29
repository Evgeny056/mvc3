package com.mvc3.handler;

import com.mvc3.exception.ErrorMessage;
import com.mvc3.exception.OrderNotFoundException;
import com.mvc3.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleOrderNotFound(OrderNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleProductNotFound(ProductNotFoundException ex) {
        ErrorMessage message = new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupportedException(Exception e) {
        ErrorMessage message = new ErrorMessage(e.getMessage());
        return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorMessage message = new ErrorMessage("Arguments not valid");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ErrorMessage message = new ErrorMessage("Invalid arguments");
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
