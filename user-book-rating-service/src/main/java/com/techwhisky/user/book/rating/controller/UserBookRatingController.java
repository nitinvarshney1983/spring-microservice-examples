package com.techwhisky.user.book.rating.controller;

import com.techwhisky.user.book.rating.bean.UserBookRatingList;
import com.techwhisky.user.book.rating.persistence.entity.UserBookRating;
import com.techwhisky.user.book.rating.service.UserBookRatingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userbookrating")
public class UserBookRatingController {

    @Autowired
    private UserBookRatingManager userBookRatingManager;

    @PostMapping()
    public ResponseEntity saveUserBookRating(@RequestBody UserBookRating userBookRating){
        userBookRatingManager.saveUserBookRating(userBookRating);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, List<UserBookRating>>> getUserBookRatingByUserId(@PathVariable("userId") String userId){
        List<UserBookRating> userBookRatings=userBookRatingManager.findByUserId(userId);

        Map<String,List<UserBookRating>> ratingsMap=userBookRatings.stream()
                .collect(Collectors.groupingBy(UserBookRating::getUserId,
                        Collectors.mapping(userBookRatingObj->new UserBookRating(userBookRatingObj.getIsbn(),userBookRatingObj.getRating())
                                ,Collectors.toList())));
        return ResponseEntity.ok(ratingsMap);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<UserBookRatingList> getUserBookRatingByIsbn(@PathVariable("isbn") String isbn){
        List<UserBookRating> userBookRatings=userBookRatingManager.findByIsbn(isbn);

        Map<String,List<UserBookRating>> ratingsMap=userBookRatings.stream()
                .collect(Collectors.groupingBy(UserBookRating::getIsbn,
                        Collectors.mapping(userBookRatingObj->new UserBookRating(userBookRatingObj.getRating(),userBookRatingObj.getUserId())
                                ,Collectors.toList())));

        UserBookRatingList userBookRatingList=new UserBookRatingList();
        userBookRatingList.setUserBookRatings(ratingsMap.get(isbn));
        return ResponseEntity.ok(userBookRatingList);
    }
}
