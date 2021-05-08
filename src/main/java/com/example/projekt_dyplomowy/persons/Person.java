package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true, length = 50)
    String username;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;

    @Enumerated(EnumType.STRING)
    State state = State.PENDING;

    public Person(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities;
}
