package com.softserve.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "form", schema = "librarydb")
public class Form {

    @Id
    @GeneratedValue
    private long id;
    private Date startDate;
    private Date returnDate;
    private Date bookReturned;

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "BookID")
    private Book FormBook;

    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "UserID")
    private User FormUser;

}
