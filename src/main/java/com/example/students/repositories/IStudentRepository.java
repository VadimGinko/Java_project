package com.example.students.repositories;

import com.example.students.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IStudentRepository  extends JpaRepository<Student, Integer> {
}
