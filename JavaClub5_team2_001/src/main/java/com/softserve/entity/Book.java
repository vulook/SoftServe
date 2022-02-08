package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book", schema = "librarydb")
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String bookName;
    private String genre;
    private long authorId;
    private int count;
    private int pageCount;
    private Integer ratings;

    @ManyToOne
    @JoinColumn(name = "AuthorID", referencedColumnName = "ID")
    private Author authorById;

    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Cart> cartsById;

    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Form> formsById;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

}
