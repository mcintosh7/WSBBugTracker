package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.issues.Issue;
import com.example.projekt_dyplomowy.validators.UniqueUsername;
import com.example.projekt_dyplomowy.validators.ValidPasswords;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ValidPasswords
@UniqueUsername
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Column(nullable = false, unique = true, length = 50)
    String username;

    @NotEmpty
    @Column(nullable = false)
    String name;

    @NotEmpty
    @Column(nullable = false)
    String password;

    @Transient
    String repeatedPassword;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;

    @NotEmpty
    @Column(nullable = false)
    String email;

    public Person(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "assignee")
    @JsonIgnoreProperties("assignee")
    Set<Issue> issues;

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnoreProperties("createdBy")
    Set<Issue> createdBy;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities;
}
