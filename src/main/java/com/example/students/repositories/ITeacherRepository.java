package com.example.students.repositories;

import com.example.students.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ITeacherRepository extends JpaRepository<Teacher, Integer>  {
}
