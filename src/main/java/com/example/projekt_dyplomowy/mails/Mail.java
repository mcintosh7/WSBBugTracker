package com.example.projekt_dyplomowy.mails;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Mail {

    String sender;
    String subject;
    String content;

    MultipartFile attachment;

}
