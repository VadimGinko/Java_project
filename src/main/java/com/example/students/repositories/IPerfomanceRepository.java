package com.example.students.repositories;

import com.example.students.model.AcademicPerformance;
import com.example.students.model.Subject;
import com.example.students.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPerfomanceRepository  extends JpaRepository<AcademicPerformance, Integer>  {
    <S extends AcademicPerformance> S save(S s);
    List<AcademicPerformance> findByUser(User user);
    Page<AcademicPerformance> findByUserAndSubject(User user, Subject subject, Pageable pageable);
    Page<AcademicPerformance> findByUser(User user, Pageable pageable);
}
