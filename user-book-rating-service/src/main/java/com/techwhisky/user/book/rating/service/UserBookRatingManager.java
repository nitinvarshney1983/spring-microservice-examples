package com.techwhisky.user.book.rating.service;

import com.techwhisky.user.book.rating.persistence.entity.UserBookRating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBookRatingManager {

    void saveUserBookRating(UserBookRating userBookRating);

      List<UserBookRating> findByIsbn(String isbn);

      List<UserBookRating> findByUserId(String userId);

}
