package com.techwhisky.book.details.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorBean {
    private int errorCode;
    private String message;
}
