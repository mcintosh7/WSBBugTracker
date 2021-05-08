package com.example.projekt_dyplomowy.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    String name;

}
