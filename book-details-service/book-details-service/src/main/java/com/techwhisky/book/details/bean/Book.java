package com.techwhisky.book.details.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book {
    private String isbn;
    private String name;
    private String description;
}
