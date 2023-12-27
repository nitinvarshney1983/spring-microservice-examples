package com.techwhisky.book.details.service.impl;

import com.techwhisky.book.details.bean.*;
import com.techwhisky.book.details.exception.ErrorBean;
import com.techwhisky.book.details.exception.WebException;
import com.techwhisky.book.details.service.BookDetailManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDetailManagerImpl implements BookDetailManager {

    @Value("${book.description.service.endpoint}")
    private String bookDescriptionServiceEndPoint;

    @Value("${user.rating.service.endpoint}")
    private String userBookRatingServiceEndPoint;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BookDetails getBookDetailsWithRating(String isbn) {

        Book book = getBookDescription(isbn);
        if(book!=null){
            List<UserBookRating> userBookRatings = getUserBookRatings(isbn);
            double avgRating=0;
            if(!CollectionUtils.isEmpty(userBookRatings)){
                avgRating=userBookRatings.stream().collect(Collectors.averagingDouble(UserBookRating::getRating));
            }
            return getBookDetails(book, avgRating);
        }else{
            throw new RuntimeException("some error occurred while processing request");
        }
    }

    @NotNull
    private static BookDetails getBookDetails(Book book, double avgRating) {
        BookDetails bookDetails=new BookDetails();
        bookDetails.setIsbn(book.getIsbn());
        bookDetails.setName(book.getName());
        bookDetails.setDescription(book.getDescription());
        bookDetails.setCalculatedRating(avgRating);
        return bookDetails;
    }

    @Nullable
    private List<UserBookRating> getUserBookRatings(String isbn) {
        ResponseEntity<UserBookRatingList> ratingListResponseEntity=null;
        List<UserBookRating> userBookRatings=null;
        try{
            ratingListResponseEntity=restTemplate.exchange(userBookRatingServiceEndPoint+"/book/"+ isbn, HttpMethod.GET,null,UserBookRatingList.class);
            if(ratingListResponseEntity.getStatusCode()== HttpStatusCode.valueOf(200)){
                userBookRatings=ratingListResponseEntity.getBody().getUserBookRatings();
            }
        }catch(RestClientException restClientException){
            HttpClientErrorException exception=(HttpClientErrorException)restClientException;
            ErrorBean errorBean=exception.getResponseBodyAs(ErrorBean.class);
            if(errorBean!=null){
                userBookRatings= Arrays.asList(new UserBookRating(null,null,0));
            }
        }
        return userBookRatings;
    }


    @Nullable
    private Book getBookDescription(String isbn) {
        ResponseEntity<Book> responseEntity=null;
        Book book=null;
        try{
            responseEntity=restTemplate.exchange(bookDescriptionServiceEndPoint+"/"+ isbn, HttpMethod.GET,null,Book.class);
            if(responseEntity.getStatusCode()== HttpStatusCode.valueOf(200)){
                book=responseEntity.getBody();
            }
        }catch(RestClientException restClientException){
            HttpClientErrorException exception=(HttpClientErrorException)restClientException;
            throw new WebException(exception.getResponseBodyAs(ErrorBean.class));
        }
        return book;
    }
}
