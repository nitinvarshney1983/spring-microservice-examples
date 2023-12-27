package com.techwhisky.book.description.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private int errorCode;
    private String message;

    public BusinessException(String message){
        this.message=message;
    }

    public BusinessException(int errorCode, String message){
        this.errorCode=errorCode;
        this.message=message;
    }


}
