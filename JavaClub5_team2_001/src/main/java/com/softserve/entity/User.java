package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "user", schema = "librarydb")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String age;
    private String phone;
    private String email;
    private String password;
    private Date regDate;

    @ManyToOne
    @Cascade({CascadeType.ALL})
    @JoinColumn(name = "UserRoleId")
    private UserRole role;

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "CartUser")
    private List<Cart> cartList = new LinkedList<>();

    @Setter(AccessLevel.PRIVATE)
    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "FormUser")
    private List<Form> formList = new LinkedList<>();

}
