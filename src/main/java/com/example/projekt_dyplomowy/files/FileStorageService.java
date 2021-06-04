package com.example.projekt_dyplomowy.files;

import com.example.projekt_dyplomowy.issues.Issue;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileStorageService {

    final FileRepository fileRepository;

    public FileStorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileDB store(MultipartFile file, Issue issue) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), issue);

        return fileRepository.save(FileDB);
    }

    public FileDB  store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(FileDB);
    }

    public FileDB getFile(Long id) {
        return fileRepository.findById(id).get();
    }

    public List<FileDB> getFilesByIssue(Issue issue) {
        return fileRepository.findAllByIssue(issue);
    }
}
