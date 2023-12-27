package com.techwhisky.book.details.controller;


import com.techwhisky.book.details.bean.BookDetails;
import com.techwhisky.book.details.service.BookDetailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookDetailsController {

    @Autowired
    private BookDetailManager bookDetailManager;

    @GetMapping("/{isbn}")
    public BookDetails getBookDetailsByISBN(@PathVariable("isbn") String isbn){
       return bookDetailManager.getBookDetailsWithRating(isbn);
    }

}
