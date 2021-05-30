package com.example.projekt_dyplomowy.comments;

import com.example.projekt_dyplomowy.issues.Issue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    Date dateCreated = new Date();

    @Column(columnDefinition = "TEXT")
    String content;

    @Column(updatable = false)
    @CreatedBy
    String createdBy;

    @CreatedDate
    @Column(updatable = false)
    Date createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "issue_id", nullable = false)
    Issue issue;



}
