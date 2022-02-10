package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "AuthorID", referencedColumnName = "ID")
    private Author authorById;

    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Cart> cartsById;

    @OneToMany(mappedBy = "bookByBookId")
    private Collection<Form> formsById;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

}
