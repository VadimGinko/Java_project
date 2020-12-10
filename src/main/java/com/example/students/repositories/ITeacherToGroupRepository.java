package com.example.students.repositories;

import com.example.students.model.TeacherToGroup;
import com.example.students.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherToGroupRepository extends JpaRepository<User, Long> {
    <S extends TeacherToGroup> S save(S s);
}