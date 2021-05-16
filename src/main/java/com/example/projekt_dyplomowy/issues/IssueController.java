package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.persons.PersonRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issue")
public class IssueController {

    final IssueRepository issueRepository;
    final IssueService issueService;
    final PersonRepository personRepository;

    public IssueController(IssueRepository issueRepository, IssueService issueService, PersonRepository personRepository) {
        this.issueRepository = issueRepository;
        this.issueService = issueService;
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("issue/index");
        modelAndView.addObject("issues", issueRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_USERS_TAB")
    ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("issues", new Issue());
        modelAndView.addObject("people", personRepository.findAll());
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
}
