package com.example.projekt_dyplomowy.projects;

import com.example.projekt_dyplomowy.persons.AuthorityRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/project")
public class ProjectController {

    final ProjectRepository projectRepository;
    final AuthorityRepository authorityRepository;

    public ProjectController(ProjectRepository projectRepository, AuthorityRepository authorityRepository){
        this.projectRepository = projectRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("project/index");
        modelAndView.addObject("projects", projectRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_USERS_TAB")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("projects", projectRepository.findAll());
        return modelAndView;
    }
}
