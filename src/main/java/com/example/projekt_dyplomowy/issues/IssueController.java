package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.enums.Priority;
import com.example.projekt_dyplomowy.enums.State;
import com.example.projekt_dyplomowy.enums.Type;
import com.example.projekt_dyplomowy.files.FileRepository;
import com.example.projekt_dyplomowy.files.FileStorageService;
import com.example.projekt_dyplomowy.mails.Mail;
import com.example.projekt_dyplomowy.mails.MailService;
import com.example.projekt_dyplomowy.persons.PersonRepository;
import com.example.projekt_dyplomowy.persons.PersonService;
import com.example.projekt_dyplomowy.projects.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/issue")
public class IssueController {

    final IssueRepository issueRepository;
    final IssueService issueService;
    final PersonService personService;
    final PersonRepository personRepository;
    final ProjectRepository projectRepository;
    final MailService mailService;
    final FileStorageService fileStorageService;
    final FileRepository fileRepository;

    public IssueController(IssueRepository issueRepository, IssueService issueService, PersonService personService, PersonRepository personRepository, ProjectRepository projectRepository, MailService mailService, FileStorageService fileStorageService, FileRepository fileRepository) {
        this.issueRepository = issueRepository;
        this.issueService = issueService;
        this.personService = personService;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
        this.mailService = mailService;
        this.fileStorageService = fileStorageService;
        this.fileRepository = fileRepository;
    }


    @GetMapping("/create")
        ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("issue", new Issue());
        modelAndView.addObject("people", personService.findAllUsers());
        modelAndView.addObject("projects", projectRepository.findByEnabled(true));
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());

        return modelAndView;
    }

    @GetMapping("/preview/{id}")
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
        modelAndView.addObject("files", fileStorageService.getFilesByIssue(issue));
        return modelAndView;
    }

    @PostMapping(value = "/save")
        ModelAndView save(@ModelAttribute @Valid Issue issue, BindingResult bindingResult, Mail mail) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("issue/create");
            modelAndView.addObject("issue", issue);
            modelAndView.addObject("projects", projectRepository.findByEnabled(true));
            modelAndView.addObject("types", Type.values());
            modelAndView.addObject("priorities", Priority.values());
            modelAndView.addObject("people", personService.findAllUsers());
            return modelAndView;
        }

        mailService.sendToAssignee(issue);
        issueService.saveIssue(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
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
        modelAndView.addObject("types", Type.values());
        modelAndView.addObject("priorities", Priority.values());
        modelAndView.addObject("states", State.values());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView delete(@PathVariable("id") Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("issue/index");
        if (issue == null) {
            modelAndView.setViewName("redirect:/issue/");
            return modelAndView;
        }

        issueService.deleteIssue(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }

    @GetMapping
    ModelAndView index(@ModelAttribute IssueFilter issueFilter, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("issue/index");

        Page<Issue> issues = issueRepository.findAll(issueFilter.buildQuery(), pageable);
        modelAndView.addObject("issues", issues);
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());
        modelAndView.addObject("states", State.values());
        modelAndView.addObject("type", Type.values());
        modelAndView.addObject("priority", Priority.values());

        modelAndView.addObject("filter", issueFilter);

        return modelAndView;
    }
}
