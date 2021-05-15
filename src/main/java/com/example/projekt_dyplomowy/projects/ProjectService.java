package com.example.projekt_dyplomowy.projects;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    protected void deleteProject(Project project){
        project.setEnabled(false);
        projectRepository.save(project);
    }

    protected void saveProject(Project project) {
        projectRepository.save(project);
    }
}
