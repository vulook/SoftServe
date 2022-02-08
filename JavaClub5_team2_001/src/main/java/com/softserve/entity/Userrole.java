package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userrole", schema = "librarydb")
public class Userrole {

    @Id
    @GeneratedValue
    private int id;
    private String role;

    @OneToMany(mappedBy = "userroleByUserRoleId")
    private Collection<User> usersById;

}
