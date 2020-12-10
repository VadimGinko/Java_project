package com.example.students.services;

import com.example.students.dto.TeacherToGroupDto;
import com.example.students.model.*;
import com.example.students.repositories.IFacultyRepository;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.repositories.ITeacherToGroupRepository;
import com.example.students.repositories.IUserRepository;
import com.example.students.services.serviceInterfaces.ITeacherToGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class TeacherToGroupService implements ITeacherToGroupService {
    private final ITeacherToGroupRepository iTeacherToGroupRepository;
    private final IFacultyRepository iFacultyRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;

    @Autowired
    public TeacherToGroupService(ITeacherToGroupRepository iTeacherToGroupRepository, IFacultyRepository iFacultyRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.iTeacherToGroupRepository = iTeacherToGroupRepository;
        this.iFacultyRepository = iFacultyRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }


    @Override
    public void addRecord(TeacherToGroupDto teacherToGroupDto) {
        Faculty faculty = iFacultyRepository.findByFaculty(teacherToGroupDto.getFaculty());
        Subject subject = iSubjectRepository.findBySubject(teacherToGroupDto.getSubject()).get(0);
        Integer group = teacherToGroupDto.getTgroup();
        Integer course = teacherToGroupDto.getCourse();
        User user1 = iUserRepository.findByUsername(teacherToGroupDto.getUser());
        TeacherToGroup teacherToGroup = new TeacherToGroup(faculty, course, group, user1, subject);
        teacherToGroup.setStatus(Status.ACTIVE);
        teacherToGroup.setCreated(new Date());
        teacherToGroup.setUpdated(new Date());
        iTeacherToGroupRepository.save(teacherToGroup);
    }
}
