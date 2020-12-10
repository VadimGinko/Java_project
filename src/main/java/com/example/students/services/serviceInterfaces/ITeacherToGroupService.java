package com.example.students.services.serviceInterfaces;

import com.example.students.dto.TeacherToGroupDto;
import com.example.students.model.TeacherToGroup;
import com.example.students.model.User;

import java.util.List;

public interface ITeacherToGroupService {
    void addRecord(TeacherToGroupDto teacherToGroupDto);
    List<User> getStudents(String facultyName, String subjectName, Integer groupValue, Integer courseValue, String username);
}
