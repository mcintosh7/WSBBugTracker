package com.example.projekt_dyplomowy.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@RevisionEntity(AuditingRevisionListener.class)
@Entity
public class AuditedRevisionEntity extends DefaultRevisionEntity {

    @Setter
    @Getter
    private String actor;

}
