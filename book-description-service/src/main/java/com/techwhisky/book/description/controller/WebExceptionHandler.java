package com.techwhisky.book.description.controller;

import com.techwhisky.book.description.controller.bean.ErrorBean;
import com.techwhisky.book.description.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorBean> handleBusinessException(BusinessException businessException){
       ErrorBean errorBean=new ErrorBean(businessException.getErrorCode(),businessException.getMessage());
       int statusCode=Integer.parseInt(Integer.toString(businessException.getErrorCode()).substring(0,3));
       ResponseEntity<ErrorBean> errorBeanResponseEntity=ResponseEntity.status(statusCode).body(errorBean);
       return errorBeanResponseEntity;
    }
}
