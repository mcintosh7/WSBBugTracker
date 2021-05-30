package com.example.projekt_dyplomowy.comments;

import com.example.projekt_dyplomowy.issues.Issue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    Date dateCreated = new Date();

    @Column(columnDefinition = "TEXT")
    String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    Issue issue;

}
