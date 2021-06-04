package com.example.projekt_dyplomowy.files;

import com.example.projekt_dyplomowy.issues.Issue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    final FileStorageService fileStorageService;
    final FileRepository fileRepository;

    public FileController(FileStorageService fileStorageService, FileRepository fileRepository) {
        this.fileStorageService = fileStorageService;
        this.fileRepository = fileRepository;
    }

    @GetMapping("/add")
    public String addfile() {
        return "file/upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Issue issue) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Nie można oddawać pustych plików");
        } else {
            try {
                fileStorageService.store(file, issue);
                attributes.addFlashAttribute("message", "Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                attributes.addFlashAttribute("message", "Could not upload the file: " + file.getOriginalFilename());            }
        }
        return "redirect:/issue/";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        FileDB fileDB = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

}
