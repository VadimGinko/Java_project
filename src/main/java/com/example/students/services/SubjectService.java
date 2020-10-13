package com.example.students.services;

import com.example.students.model.Subject;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.services.serviceInterfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectService implements ISubjectService {
    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return this.subjectRepository.findAll();
    }
}
