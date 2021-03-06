package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.projects.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.Configuration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${my.admin.username}")
    private String myAdminUsername;

    @Value("${my.admin.password}")
    private String myAdminPassword;

    public PersonService(AuthorityRepository authorityRepository,
                         PersonRepository personRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void prepareAdminUser() {

        if (personRepository.findByUsername(myAdminUsername) != null) {
            System.out.println("Użytkownik " + myAdminUsername + " już istnieje. Przerywamy tworzenie.");
            return;
        }

        System.out.println("Tworzymy administratora: " + myAdminUsername + "...");

        Person person = new Person(myAdminUsername, myAdminPassword, "Administrator", "pg21498@student.wsb.gda.pl");

        List<Authority> authorities = authorityRepository.findAll();
        person.setAuthorities(new HashSet<>(authorities));

        savePerson(person);
    }

    public void savePerson(Person person) {
        String hashedPassword = bCryptPasswordEncoder.encode(person.password);
        person.setPassword(hashedPassword);

        personRepository.save(person);
    }

    public void savePerson(PersonForm personForm) {
        Person person = personRepository.findById(personForm.id).orElse(null);
        person.username = personForm.username;
        person.name = personForm.name;
        person.email = personForm.email;
        person.authorities = personForm.authorities;
        personRepository.save(person);
    }

    public List<Person> findAllUsers() {
        return personRepository.findAll();
    }

    protected void deletePerson(Person person){
        person.setEnabled(false);
        personRepository.save(person);
    }

    public void updatePassword(PasswordForm passwordForm) {
        Person person = personRepository.findById(passwordForm.id).orElse(null);
        person.password = bCryptPasswordEncoder.encode(passwordForm.password);
        personRepository.save(person);

    }

    }



