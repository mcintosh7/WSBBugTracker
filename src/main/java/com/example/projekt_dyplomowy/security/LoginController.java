package com.example.projekt_dyplomowy.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String index() {
        return "security/login";
    }
}
