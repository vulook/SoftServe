package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "book", schema = "librarydb")
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String bookName;
    private String author;
    private String genre;
    private long authorId;
    private int count;
    private int pageCount;
    private Integer ratings;

    @ManyToOne
    @ReadOnlyProperty
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "AuthorID")
    private Author mainAuthor;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(name = "coauthor",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "AuthorID")
    )
    private Set<Author> co_authors = new HashSet<>();

}
