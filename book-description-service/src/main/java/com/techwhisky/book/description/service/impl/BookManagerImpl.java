package com.techwhisky.book.description.service.impl;

import com.techwhisky.book.description.exception.BusinessException;
import com.techwhisky.book.description.service.BookManager;
import com.techwhisky.book.description.persistence.entity.Book;
import com.techwhisky.book.description.persistence.repository.BookRepositoryMongo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class BookManagerImpl implements BookManager {

    private BookRepositoryMongo bookRepositoryMongo;

    @Autowired
    public BookManagerImpl(BookRepositoryMongo bookRepositoryMongo){
        this.bookRepositoryMongo=bookRepositoryMongo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryMongo.findAll();
    }

    @Override
    public void saveBook(Book book) {
       bookRepositoryMongo.findById(book.getIsbn())
               .ifPresent((book1)->{
                   throw new BusinessException(41201,"book.already.exist");
               });
        bookRepositoryMongo.save(book);
    }

    @Override
    public void updateBook(Book book, String isbn) {

        Predicate<String> isbnPredicate=(bookNumber)->bookNumber.equals(book.getIsbn());
        if(!isbnPredicate.test(isbn)){
            throw new BusinessException(40001,"request.not.valid");
        }

        Optional<Book> bookOptional=bookRepositoryMongo.findById(isbn);
        if(bookOptional.isPresent()){
            Book existingBook=bookOptional.get();
            if(!StringUtils.isEmpty(book.getName()))
                existingBook.setName(book.getName());
            if(!StringUtils.isEmpty(book.getDescription()))
                existingBook.setDescription(book.getDescription());
            bookRepositoryMongo.save(existingBook);
        }else{
            throw new BusinessException(41202, "book.not.exist");
        }
     }

    @Override
    public void deleteBook(String isbn) {
        try {
            Optional<Book> bookOptional = bookRepositoryMongo.findById(isbn);
            Book book=new Book();
            book.setIsbn(isbn);
            bookRepositoryMongo.delete(book);
          } catch (Exception exception) {
            throw new BusinessException(40401, "book.not.exist");
        }
    }

    @Override
    public Book getBookByISBN(String isbn) {
        try {
            Optional<Book> bookOptional = bookRepositoryMongo.findById(isbn);
            return bookOptional.get();
        } catch (Exception exception) {
            throw new BusinessException(40401, "book.not.exist");
        }
    }
}
