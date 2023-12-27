package com.techwhisky.book.details.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(WebException.class)
    public ResponseEntity<ErrorBean> handleWebException(WebException exception){
        return ResponseEntity.ok(exception.getErrorBean());
    }
}
