package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.enums.AuthorityName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Authority {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    @Enumerated
    AuthorityName name;

    public Authority(AuthorityName name) {
        this.name = name;
    }
}
