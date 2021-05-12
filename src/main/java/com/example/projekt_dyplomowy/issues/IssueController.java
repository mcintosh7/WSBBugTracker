package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.persons.AuthorityRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issue")
public class IssueController {

    final IssueRepository issueRepository;
    private final AuthorityRepository authorityRepository;

    public IssueController(IssueRepository issueRepository, AuthorityRepository authorityRepository) {
        this.issueRepository = issueRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("issue/index");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("issue", issueRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_CREATE_TAB")
    ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("issue", new Issue());
        return modelAndView;
    }
}
