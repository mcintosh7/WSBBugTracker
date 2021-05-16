package com.example.projekt_dyplomowy.projects;

import com.example.projekt_dyplomowy.issues.Issue;
import com.example.projekt_dyplomowy.validators.UniqueProjectName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@UniqueProjectName
public class Project {

    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;

    @NotEmpty
    @Column
    String description;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    Set<Issue> issues;

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
