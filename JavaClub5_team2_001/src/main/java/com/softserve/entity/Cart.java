package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "cart", schema = "librarydb")
public class Cart {

    @Id
    @GeneratedValue
    private long id;
    private byte action;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "BookID")
    private Book CartBook;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "UserID")
    private User CartUser;

}
