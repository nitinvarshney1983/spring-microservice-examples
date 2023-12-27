package com.techwhisky.user.book.rating.persistence.entity;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserBookKey implements Serializable {

    private String userId;

    private String isbn;
}
