package com.techwhisky.book.description.persistence.repository;

import com.techwhisky.book.description.persistence.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryMongo extends MongoRepository<Book,String> {

}
