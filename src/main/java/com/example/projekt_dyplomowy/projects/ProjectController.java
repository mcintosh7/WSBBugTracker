package com.example.projekt_dyplomowy.projects;

import com.example.projekt_dyplomowy.persons.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("project/index");
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView save(@ModelAttribute @Valid Project project, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("project/create");
            modelAndView.addObject(project);
            return modelAndView;
        }
        projectService.saveProject(project);
        modelAndView.setViewName("redirect:/project/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView edit(@PathVariable("id") Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("project/edit");
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @GetMapping("/preview/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView preview(@PathVariable("id") Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("project/preview");
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView delete(@PathVariable ("id") Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("project/create");
        projectService.deleteProject(project);
        modelAndView.setViewName("redirect:/project/");
        return modelAndView;
    }
}
