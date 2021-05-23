package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.projects.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>, JpaSpecificationExecutor<Issue> {

    List<Issue> findByEnabled(Boolean enabled);

}
