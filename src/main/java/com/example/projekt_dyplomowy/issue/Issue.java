package com.example.projekt_dyplomowy.issue;

import com.example.projekt_dyplomowy.person.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Issue {

    @Id
    @GeneratedValue
    Long id;

    Enum status;
    Enum priority;
    Enum type;
    String name;
    String description;
    String code;
    //Person creator;
    //Person assignee;
    Date dateCreated;
    Date lastUpdate;

}
