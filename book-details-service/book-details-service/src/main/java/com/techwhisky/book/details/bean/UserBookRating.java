package com.techwhisky.book.details.bean;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBookRating {

    private String userId;

    private String isbn;

    private double rating;


}
