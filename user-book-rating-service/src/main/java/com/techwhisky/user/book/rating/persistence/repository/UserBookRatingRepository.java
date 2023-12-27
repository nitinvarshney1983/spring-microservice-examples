package com.techwhisky.user.book.rating.persistence.repository;

import com.techwhisky.user.book.rating.persistence.entity.UserBookKey;
import com.techwhisky.user.book.rating.persistence.entity.UserBookRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRatingRepository extends CrudRepository<UserBookRating, UserBookKey> {

    List<UserBookRating> findByUserId(String userId);

    List<UserBookRating> findByIsbn(String isbn);
}
