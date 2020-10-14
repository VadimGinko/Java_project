package com.example.students.repositories;

import com.example.students.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findBySubject(String subject);
}
