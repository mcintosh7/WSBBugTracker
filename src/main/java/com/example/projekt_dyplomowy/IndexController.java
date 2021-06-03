package com.example.projekt_dyplomowy;

import com.example.projekt_dyplomowy.persons.PasswordForm;
import com.example.projekt_dyplomowy.persons.PersonService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("isAdmin", true);
        model.addAttribute("message","błąd taki");
        model.addAttribute("type","danger");

        return "redirect:/issue/";
    }

    @GetMapping("/contact")
    @Secured(("ROLE_USERS_TAB"))
    public String contact() {
        return "contact";
    }



}
