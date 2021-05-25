package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.enums.Priority;
import com.example.projekt_dyplomowy.enums.State;
import com.example.projekt_dyplomowy.enums.Type;
import com.example.projekt_dyplomowy.persons.Person;
import com.example.projekt_dyplomowy.projects.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Issue {


    public Issue(String title, String content, State state, Person name, Project project, Priority priority, Type type, Person creator) {
        this.title = title;
        this.content = content;
        this.state = state;
        this.assignee = name;
        this.project = project;
        this.priority = priority;
        this.type = type;
    }

    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Column(nullable = false)
    String title;

    @Column(columnDefinition = "TEXT")
    String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    State state = State.NEW;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    Priority priority;

    @Column(nullable = false)
    LocalDateTime dateCreated = LocalDateTime.now();

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Type type;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;

    /*@CreatedBy
    Person createdBy;*/

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "assignee_id", nullable = false)
    Person assignee;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

}
