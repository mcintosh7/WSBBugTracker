package com.example.projekt_dyplomowy.files;

import com.example.projekt_dyplomowy.issues.Issue;
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

    public FileDB(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
    }

    public FileDB(String fileName, String contentType, byte[] bytes, Issue issue) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
        this.issue = issue;
    }
}
