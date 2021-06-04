package com.example.projekt_dyplomowy.files;

import com.example.projekt_dyplomowy.issues.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileDB, Long> {

    List<FileDB> findAllByIssue(Issue issue);
}
