package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form", schema = "librarydb")
public class Form {

    @Id
    @GeneratedValue
    private long id;
//    private Long userId;
//    private long bookId;
    private Date startDate;
    private Date returnDate;
    private Date bookReturned;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "ID")
    private User userByUserId;

    @ManyToOne
    @JoinColumn(name = "BookID", referencedColumnName = "ID")
    private Book bookByBookId;

}
