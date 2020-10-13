package com.example.students.model;

import javax.persistence.*;

@Entity
public class Subject {
    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    @Id
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
