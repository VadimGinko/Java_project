package com.example.students.services;

import com.example.students.model.Faculty;
import com.example.students.model.Subject;
import com.example.students.repositories.IFacultyRepository;
import com.example.students.services.serviceInterfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Autowired
    public FacultyService(IFacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> findAll() {
        return null;
    }
}
