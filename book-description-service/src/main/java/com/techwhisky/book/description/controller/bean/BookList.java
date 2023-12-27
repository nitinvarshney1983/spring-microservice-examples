package com.techwhisky.book.description.controller.bean;

import com.techwhisky.book.description.persistence.entity.Book;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BookList {
    private List<Book> books;
}
