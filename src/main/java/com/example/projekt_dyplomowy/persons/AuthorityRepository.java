package com.example.projekt_dyplomowy.persons;

import com.example.projekt_dyplomowy.enums.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName name);
}

