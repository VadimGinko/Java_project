package com.example.students.services;

import com.example.students.model.Faculty;
import com.example.students.model.Status;
import com.example.students.model.Subject;
import com.example.students.repositories.IFacultyRepository;
import com.example.students.services.serviceInterfaces.IFacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class FacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Autowired
    public FacultyService(IFacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> findAll() {
        log.info("FacultyService : find All Faculty");
        return facultyRepository.findAll();
    }

    @Override
    public void addFaculty(String facultyName) {
        Faculty faculty = new Faculty();
        faculty.setFaculty(facultyName);
        log.info("FacultyService : add Faculty");
        this.facultyRepository.save(faculty);
    }
}
