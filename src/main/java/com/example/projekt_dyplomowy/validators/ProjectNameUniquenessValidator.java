package com.example.projekt_dyplomowy.validators;

import com.example.projekt_dyplomowy.projects.Project;
import com.example.projekt_dyplomowy.projects.ProjectRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProjectNameUniquenessValidator
        implements ConstraintValidator<UniqueProjectName, Project> {

    private final ProjectRepository projectRepository;

    public ProjectNameUniquenessValidator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void initialize(UniqueProjectName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Project project, ConstraintValidatorContext ctx) {
        Project foundProject = projectRepository.findByName(project.getName());

        if (foundProject == null) {
            return true;
        }

        boolean projectNameIsUnique = project.getId() != null && foundProject.getId().equals(project.getId());

        if (!projectNameIsUnique) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("name")
                    .addConstraintViolation();
        }

        return projectNameIsUnique;
    }
}
