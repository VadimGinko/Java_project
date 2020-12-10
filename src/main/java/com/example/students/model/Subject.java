package com.example.students.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "subjects")
@Getter
public class Subject extends BaseEntity{
    public Subject() {
    }

    public Subject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
