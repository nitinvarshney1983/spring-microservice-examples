package com.techwhisky.book.details.exception;


import lombok.Getter;

@Getter
public class WebException extends RuntimeException{

    private ErrorBean errorBean;

    public WebException(ErrorBean errorBean){
        this.errorBean=errorBean;
    }
}
