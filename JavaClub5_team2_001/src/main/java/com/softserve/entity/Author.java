package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "Author")
public class Author {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.SAVE_UPDATE})
    @OneToMany(mappedBy = "mainAuthor")
    private Set<Book> books = new HashSet<>();

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.ALL})
    @ManyToMany(mappedBy = "co_authors")
//    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST,org.hibernate.annotations.CascadeType.DELETE})
    private Set<Book> coAuthorBooks = new HashSet<>();

}
