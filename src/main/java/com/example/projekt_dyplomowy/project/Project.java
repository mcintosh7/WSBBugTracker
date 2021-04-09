package com.example.projekt_dyplomowy.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    //Set<Issue> issues;
    //Boolean enabled;
    private Date dateCreated;
    private String code;
    private String description;
}
