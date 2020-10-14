package com.example.students.services.serviceInterfaces;

import com.example.students.model.Subject;
import com.example.students.model.Teacher;

import java.util.List;

public interface ITeacherService {
    List<Teacher> findAll();
}
