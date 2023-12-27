package com.techwhisky.book.details.service;

import com.techwhisky.book.details.bean.BookDetails;

import java.util.List;

/*
  A hypothetical implementation that will cover
      1- An individual Book with Average Rating by different Users
 */

public interface BookDetailManager {

    public BookDetails getBookDetailsWithRating(String isbn);
}
