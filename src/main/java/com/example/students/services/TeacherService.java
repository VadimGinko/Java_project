package com.example.students.services;

import com.example.students.model.Subject;
import com.example.students.model.Teacher;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.repositories.ITeacherRepository;
import com.example.students.services.serviceInterfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    @Autowired
    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<Teacher> findAll() {
        return null;
    }
}
