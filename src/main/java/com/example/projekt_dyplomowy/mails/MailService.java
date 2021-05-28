package com.example.projekt_dyplomowy.mails;

import com.example.projekt_dyplomowy.issues.Issue;
import com.example.projekt_dyplomowy.persons.Person;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    final JavaMailSender javaMailSender;
    public Issue issue;
    public Person person;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    void send(Mail mail) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo("testmailwsb@gmail.com");
            mimeMessageHelper.setSubject(mail.subject);
            mimeMessageHelper.setText("Wiadomość od: " + mail.sender +"\r\n"+ mail.content);

            mimeMessageHelper.addAttachment(mail.attachment.getOriginalFilename(), mail.attachment);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            System.out.println("Wysyłanie mejla nie powiodło się " + e);
        }
    }

    public void sendToAssignee(Issue issue) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(issue.getAssignee().getEmail());
            mimeMessageHelper.setSubject(issue.getTitle());
            mimeMessageHelper.setText("Zgłoszenie zostało zmodyfikowane lub utworzone, zapoznaj się z jego szczegółami pod adresem: " + " https://www.wsb.pl");

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            System.out.println("Wysyłanie mejla nie powiodło się " + e);
        }
    }

}