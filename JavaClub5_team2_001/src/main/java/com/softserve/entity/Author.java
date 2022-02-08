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
@Table(name = "author", schema = "librarydb")
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "coauthor",
            joinColumns = @JoinColumn(name = "BookID"),
            inverseJoinColumns = @JoinColumn(name = "AuthorID"))
    private Set<Book> books;

    @OneToMany(mappedBy = "authorById")
    private Collection<Book> authorById;

}
