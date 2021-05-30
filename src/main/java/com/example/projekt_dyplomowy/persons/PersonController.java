package com.example.projekt_dyplomowy.persons;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepository;
    private final AuthorityRepository authorityRepository;

    public PersonController(PersonService personService, PersonRepository personRepository, AuthorityRepository authorityRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("people/index");
        modelAndView.addObject("people", personService.findAllUsers());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_CREATE_USER")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("people/create");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

    @PostMapping(value = "/save")
    @Secured("ROLE_CREATE_USER")
    ModelAndView save(@ModelAttribute @Valid Person person, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
          modelAndView.setViewName("people/create");
          modelAndView.addObject("authorities", authorityRepository.findAll());
          modelAndView.addObject(person);
          return modelAndView;
        }
        personService.savePerson(person);
        modelAndView.setViewName("redirect:/people/");

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView edit(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("people/create");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject(person);
        return modelAndView;
    }

    @PostMapping(value = "/update/{id}")
    @Secured("ROLE_CREATE_USER")
    public String updateUser(@ModelAttribute("id") long id, @Valid PersonForm personForm,
                             BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            personForm.setId(id);
            model.addAttribute("personForm", personForm);
            return "person/show";
        }
        personService.savePerson(personForm);
        return "redirect:/people/";
    }


}

