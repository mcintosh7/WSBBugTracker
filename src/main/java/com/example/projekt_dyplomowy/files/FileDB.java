package com.example.projekt_dyplomowy.files;

import com.example.projekt_dyplomowy.issues.Issue;
import com.example.projekt_dyplomowy.projects.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FileDB {

    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    String name;

    @Column
    String type;

    @Lob
    byte[] data;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    Issue issue;

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
