package com.techwhisky.user.book.rating.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(UserBookKey.class)
@Table(name = "user_book_rating")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBookRating {

    @Id
    @Column(name = "user_id",nullable = false)
    private String userId;

    @Id
    @Column(name = "isbn",nullable = false)
    private String isbn;

    @Column(name = "rating", nullable = false)
    private double rating;

    public UserBookRating(String isbn,double rating){
        this.isbn=isbn;
        this.rating=rating;
    }

    public UserBookRating(double rating,String userId){
        this.userId=userId;
        this.rating=rating;
    }

}
