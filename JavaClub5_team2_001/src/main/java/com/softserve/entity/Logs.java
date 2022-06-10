package com.softserve.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs", schema = "librarydb")
public class Logs {

    @Id
    @GeneratedValue
    private long id;
    private String user;
    private Date date;
    private String type;
    private int bookId;
    private String performedBy;
    private String tableName;

}
