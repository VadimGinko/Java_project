package com.example.students.repositories;

import com.example.students.model.AcademicPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IPerfomanceRepository  extends JpaRepository<AcademicPerformance, Integer>  {
}
