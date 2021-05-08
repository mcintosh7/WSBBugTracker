package com.example.projekt_dyplomowy;

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

        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/tasks")
    public String tasks() {
        return "tasks";
    }


}
