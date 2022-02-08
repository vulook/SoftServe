package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart", schema = "librarydb")
public class Cart {

    @Id
    @GeneratedValue
    private long id;
//    private long userId;
//    private long bookId;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID")
    private Book bookByBookId;


}
