package com.example.projekt_dyplomowy.persons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PersonForm {

    Long id;

    @NotBlank
    String username;

    @NotBlank
    String name;

    @NotBlank
    String email;

    @Column(updatable = false)
    @CreatedBy
    String createdBy;

    @CreatedDate
    @Column(updatable = false)
    Date createdDate;

    @LastModifiedBy
    String lastModifiedBy;

    @LastModifiedDate
    Date lastModifiedDate;

    Set<Authority> authorities;

    public PersonForm(Person person) {
        this.id = person.id;
        this.username = person.username;
        this.name = person.name;
        this.email = person.email;
        this.authorities = person.authorities;
        this.createdDate = person.createdDate;
        this.createdBy = person.createdBy;
        this.lastModifiedBy = person.lastModifiedBy;
        this.lastModifiedDate = person.lastModifiedDate;
    }
}
