package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "author", schema = "librarydb")
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.SAVE_UPDATE})
    @OneToMany(mappedBy = "mainAuthor")
    private Set<Book> books = new HashSet<>();

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.ALL})
    @ManyToMany(mappedBy = "co_authors")
    private Set<Book> coAuthorBooks = new HashSet<>();

}
