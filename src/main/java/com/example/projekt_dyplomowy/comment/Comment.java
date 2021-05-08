package com.example.projekt_dyplomowy.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    Date dateCreated;

    @Column
    private String author;

    @Column
    String content;

}
