package com.example.projekt_dyplomowy;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
