package com.techwhisky.book.details.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDetails {

    private String isbn;
    private String name;
    private String description;
    private double calculatedRating;
}
