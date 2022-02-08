package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "librarydb")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String age;
//    private int userRoleId;
    private String phone;
    private String email;
    private String password;
    private Date regDate;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Cart> cartsById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Form> formsById;

    @ManyToOne
    @JoinColumn(name = "UserRoleID", referencedColumnName = "ID", nullable = false)
    private Userrole userroleByUserRoleId;

}