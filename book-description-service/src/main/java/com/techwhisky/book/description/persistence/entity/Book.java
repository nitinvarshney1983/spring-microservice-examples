package com.techwhisky.book.description.persistence.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
public class Book {

    @MongoId
    private String isbn;
    private String name;
    private String description;

}
