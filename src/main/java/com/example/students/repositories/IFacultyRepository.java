package com.example.students.repositories;

import com.example.students.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IFacultyRepository  extends JpaRepository<Faculty, Integer>  {
}
