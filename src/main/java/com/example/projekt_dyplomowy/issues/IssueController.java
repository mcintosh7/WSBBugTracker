package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.enums.Priority;
import com.example.projekt_dyplomowy.enums.State;
import com.example.projekt_dyplomowy.enums.Type;
import com.example.projekt_dyplomowy.persons.PersonRepository;
import com.example.projekt_dyplomowy.persons.PersonService;
import com.example.projekt_dyplomowy.projects.ProjectRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issue")
public class IssueController {

    final IssueRepository issueRepository;
    final IssueService issueService;
    final PersonService personService;
    final PersonRepository personRepository;
    final ProjectRepository projectRepository;

    public IssueController(IssueRepository issueRepository, IssueService issueService, PersonService personService, PersonRepository personRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.issueService = issueService;
        this.personService = personService;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }


    @GetMapping("/create")
    @Secured("ROLE_USERS_TAB")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("issues", new Issue());
        modelAndView.addObject("people", personService.findAllUsers());
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());

        return modelAndView;
    }

    @GetMapping("/preview/{id}")
    @Secured("ROLE_USERS_TAB")
    ModelAndView preview(@PathVariable("id") Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("issue/preview");
        if (issue == null) {
            modelAndView.setViewName("redirect:/issue/");
            return modelAndView;
        }
        modelAndView.addObject("issues", issue);
        modelAndView.addObject("people", personService.findAllUsers());
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_USERS_TAB")
    ModelAndView save(@ModelAttribute Issue issue) {
        ModelAndView modelAndView = new ModelAndView();

        issueService.saveIssue(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_USERS_TAB")
    ModelAndView edit(@PathVariable("id") Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("issue/edit");
        if (issue == null) {
            modelAndView.setViewName("redirect:/issue/");
            return modelAndView;
        }
        modelAndView.addObject("issues", issue);
        modelAndView.addObject("people", personService.findAllUsers());
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        modelAndView.addObject("states", State.values());
        return modelAndView;
    }

    @GetMapping
    @Secured("ROLE_USERS_TAB")
    ModelAndView index(@ModelAttribute IssueFilter issueFilter) {
        ModelAndView modelAndView = new ModelAndView("issue/index");

        modelAndView.addObject("issues", issueRepository.findAll(issueFilter.buildQuery()));
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());
        modelAndView.addObject("states", State.values());

        modelAndView.addObject("filter", issueFilter);

        return modelAndView;
    }
}
