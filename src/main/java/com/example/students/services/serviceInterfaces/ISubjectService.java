package com.example.students.services.serviceInterfaces;

import com.example.students.model.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    List<Subject> findBySubjectName(String subject);
}