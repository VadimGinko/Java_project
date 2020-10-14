package com.example.students.services;

import com.example.students.model.Student;
import com.example.students.model.Subject;
import com.example.students.repositories.IStudentRepository;
import com.example.students.services.serviceInterfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public List<Student> findByStudentMiddleName(String MiddleName) {
        return  this.studentRepository.findByStudentMiddleName(MiddleName);
    }
}
