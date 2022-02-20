package com.softserve.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Table(name = "userrole", schema = "librarydb")
public class UserRole {

    @Id
    @GeneratedValue
    private int id;
    private String role;

    @OneToMany(mappedBy = "role")
    private List<User> users;

}
