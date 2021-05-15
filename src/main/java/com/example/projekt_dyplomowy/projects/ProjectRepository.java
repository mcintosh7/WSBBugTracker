package com.example.projekt_dyplomowy.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByEnabled(Boolean enabled);

    Project findByName(String name);
}
