package com.example.students.services;

import com.example.students.model.Status;
import com.example.students.model.Subject;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.services.serviceInterfaces.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SubjectService implements ISubjectService {
    private final ISubjectRepository subjectRepository;

    @Autowired
    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        log.info("SubjectService : findAll");
        return this.subjectRepository.findAll();
    }

    @Override
    public void deleteSubjectByName(String subject) {
        Subject deSubject = this.subjectRepository.findBySubject(subject).get(0);
        deSubject.setStatus(Status.DELETED);
        this.subjectRepository.save(deSubject);
        log.info("SubjectService : deleteSubjectByName");
    }

    @Override
    public void addSubject(String subject) {
        Subject subject1 = new Subject();
        subject1.setStatus(Status.ACTIVE);
        subject1.setCreated(new Date());
        subject1.setUpdated(new Date());
        subject1.setSubject(subject);
        this.subjectRepository.save(subject1);
        log.info("SubjectService : addSubject");
    }

}
