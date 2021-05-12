package com.example.projekt_dyplomowy.projects;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
