package com.example.projekt_dyplomowy.mails;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mails")
public class MailController {

    final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    @Secured("ROLE_USERS_TAB")
    String getForm() {
        return "contact";
    }

    @PostMapping
    @Secured("ROLE_USERS_TAB")
    String sendMail(@ModelAttribute Mail mail) {
        mailService.send(mail);
        return "contact";
    }


}
