package com.techwhisky.book.description.service;

import com.techwhisky.book.description.persistence.entity.Book;

import java.util.List;

public interface BookManager {

    public List<Book> getAllBooks();

    public void saveBook(Book book);

    public void updateBook(Book book, String isbn);

    public void deleteBook(String isbn);

    public Book getBookByISBN(String isbn);
}
