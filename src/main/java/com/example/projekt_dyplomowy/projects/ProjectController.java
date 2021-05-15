package com.example.projekt_dyplomowy.projects;

import com.example.projekt_dyplomowy.persons.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/project")
public class ProjectController {

    final ProjectRepository projectRepository;
    final ProjectService projectService;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService){
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("project/index");
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_USERS_TAB")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("projects", new Project());
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_USERS_TAB")
    ModelAndView saveProject(@Valid @ModelAttribute Project project) {
        ModelAndView modelAndView = new ModelAndView();
        projectRepository.save(project);
        modelAndView.setViewName("redirect:/project/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_USERS_TAB")
    ModelAndView edit(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("projects", projectRepository.findById(id));
        modelAndView.addObject(project);
        return modelAndView;
    }
}
