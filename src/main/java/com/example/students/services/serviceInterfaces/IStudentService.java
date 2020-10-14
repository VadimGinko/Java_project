package com.example.students.services.serviceInterfaces;

import com.example.students.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    List<Student> findByStudentMiddleName(String MiddleName);
}
