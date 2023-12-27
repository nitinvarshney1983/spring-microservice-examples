package com.techwhisky.book.details.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserBookRatingList {

    private List<UserBookRating> userBookRatings;
}
