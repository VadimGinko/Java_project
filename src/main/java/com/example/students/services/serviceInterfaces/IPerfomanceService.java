package com.example.students.services.serviceInterfaces;

import com.example.students.dto.RateDto;
import com.example.students.model.AcademicPerformance;
import com.example.students.model.Subject;
import com.example.students.model.User;
import com.example.students.services.PerfomanceService;

import java.util.List;

public interface IPerfomanceService {
    void rate(RateDto rateDto);
    List<String> findByUser(String username);
}
