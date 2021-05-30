package com.example.projekt_dyplomowy.audit;


import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuditingRevisionListener implements RevisionListener {


    @Override
    public void newRevision(Object revisionEntity) {

        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;

        User actor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        auditedRevisionEntity.setActor(actor.getUsername());

    }
}
