package com.techwhisky.user.book.rating.bean;


import com.techwhisky.user.book.rating.persistence.entity.UserBookRating;
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
