package com.example.projekt_dyplomowy.issues;

import com.example.projekt_dyplomowy.persons.Person;
import com.example.projekt_dyplomowy.persons.PersonRepository;
import com.example.projekt_dyplomowy.projects.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    final PersonRepository personRepository;


    public IssueService(IssueRepository issueRepository, PersonRepository personRepository) {
        this.issueRepository = issueRepository;
        this.personRepository = personRepository;
    }

    List<Issue> findAllIssue() {
        return issueRepository.findAll();
    }

    protected void saveIssue(Issue issue) {
        issueRepository.save(issue);
    }

    protected void deleteIssue(Issue issue){
        issue.setEnabled(false);
        issueRepository.save(issue);
    }

    /*public issueEmail() {
        Issue
        return Person.;
    }*/


}
