package com.example.students.services.serviceInterfaces;

import com.example.students.model.Faculty;
import com.example.students.model.Subject;

import java.util.List;

public interface IFacultyService {
    List<Faculty> findAll();
}
