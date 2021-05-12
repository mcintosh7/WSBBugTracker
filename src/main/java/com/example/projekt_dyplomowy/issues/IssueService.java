package com.example.projekt_dyplomowy.issues;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    List<Issue> findAllIssue() {
        return issueRepository.findAll();
    }
}
