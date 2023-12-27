package com.techwhisky.book.description.controller;


import com.techwhisky.book.description.controller.bean.BookList;
import com.techwhisky.book.description.persistence.entity.Book;
import com.techwhisky.book.description.service.BookManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookManager bookManager;

    private BookController(BookManager bookManager){
        this.bookManager=bookManager;
    }

    @GetMapping()
    public BookList findAllBooks(){
        List<Book> books=bookManager.getAllBooks();
        BookList bookList=new BookList();
        bookList.setBooks(books);
        return bookList;
    }

    @GetMapping("/{isbn}")
    public Book getBookByISBN(@PathVariable("isbn") String isbn) throws InterruptedException {
          return bookManager.getBookByISBN(isbn);
    }

    @PostMapping
    public ResponseEntity saveBook(@RequestBody Book book){
        bookManager.saveBook(book);
        return ResponseEntity.created(URI.create("http/book-description-service/books/"+book.getIsbn())).build();
    }

    @PutMapping("/{isbn}")
    public ResponseEntity updateBook(@RequestBody Book book, @PathVariable("isbn") String isbn){
        bookManager.updateBook(book,isbn);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn){
        bookManager.deleteBook(isbn);
       return ResponseEntity.noContent().build();
    }
}
