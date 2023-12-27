package com.techwhisky.book.description.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorBean {

    private int errorCode;

    private String message;
}
