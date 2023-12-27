package com.techwhisky.book.details.bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBookRating {

    private String userId;

    private String isbn;

    private double rating;


}
