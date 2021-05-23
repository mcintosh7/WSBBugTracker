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

    /*void sendToAssignee(Mail mail) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo();
            mimeMessageHelper.setSubject(mail.subject);
            mimeMessageHelper.setText("treść wiadomości");

            mimeMessageHelper.addAttachment(mail.attachment.getOriginalFilename(), mail.attachment);

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            System.out.println("Wysyłanie mejla nie powiodło się " + e);
        }
    }*/

}