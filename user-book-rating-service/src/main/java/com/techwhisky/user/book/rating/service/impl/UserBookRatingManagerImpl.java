package com.techwhisky.user.book.rating.service.impl;

import com.techwhisky.user.book.rating.persistence.entity.UserBookRating;
import com.techwhisky.user.book.rating.persistence.repository.UserBookRatingRepository;
import com.techwhisky.user.book.rating.service.UserBookRatingManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookRatingManagerImpl implements UserBookRatingManager {

    private UserBookRatingRepository userBookRatingRepository;

    public UserBookRatingManagerImpl(UserBookRatingRepository userBookRatingRepository){
        this.userBookRatingRepository=userBookRatingRepository;
    }
    @Override
    public void saveUserBookRating(UserBookRating userBookRating) {
        userBookRatingRepository.save(userBookRating);
    }

    @Override
    public List<UserBookRating> findByIsbn(String isbn) {
        return userBookRatingRepository.findByIsbn(isbn);
    }

    @Override
    public List<UserBookRating> findByUserId(String userId) {
        return userBookRatingRepository.findByUserId(userId);
    }
}
