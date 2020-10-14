package com.example.students.services;

import com.example.students.model.Subject;
import com.example.students.repositories.IPerfomanceRepository;
import com.example.students.services.serviceInterfaces.IPerfomanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PerfomanceService implements IPerfomanceService {
    private final IPerfomanceRepository perfomanceRepository;

    @Autowired
    public PerfomanceService(IPerfomanceRepository perfomanceRepository) {
        this.perfomanceRepository = perfomanceRepository;
    }

    @Override
    public List<PerfomanceService> findAll() {
        return null;
    }
}
