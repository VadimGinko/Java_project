package com.example.students.services;

import com.example.students.dto.RateDto;
import com.example.students.mail.MyConstants;
import com.example.students.model.AcademicPerformance;
import com.example.students.model.Status;
import com.example.students.model.Subject;
import com.example.students.model.User;
import com.example.students.repositories.IPerfomanceRepository;
import com.example.students.repositories.ISubjectRepository;
import com.example.students.repositories.IUserRepository;
import com.example.students.services.serviceInterfaces.IPerfomanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PerfomanceService implements IPerfomanceService {
    private final IPerfomanceRepository perfomanceRepository;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public PerfomanceService(IPerfomanceRepository perfomanceRepository, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.perfomanceRepository = perfomanceRepository;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public void rate(RateDto rateDto) {
        AcademicPerformance academicPerformance = new AcademicPerformance();
        academicPerformance.setStatus(Status.ACTIVE);
        academicPerformance.setCreated(new Date());
        academicPerformance.setUpdated(new Date());
        academicPerformance.setDiscription(rateDto.getDescription());
        academicPerformance.setMark(rateDto.getMark());

        User user1 = iUserRepository.findByUsername(rateDto.getUsername());
        Subject subject = iSubjectRepository.findBySubject(rateDto.getSubject()).get(0);

        academicPerformance.setSubject(subject);
        academicPerformance.setUser(user1);
        perfomanceRepository.save(academicPerformance);


        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.TO_EMAIL);
        //message.setTo(user1.getEmail());
        message.setSubject("СДО BSTU");
        message.setText(String.format("Предмет: %s; Оценка: %d; %s",rateDto.getSubject(), rateDto.getMark(), rateDto.getDescription()));
        log.info("PerfomanceService : rate + mail sended");
        this.emailSender.send(message);
    }

    @Override
    public List<String> findByUser(String user) {
        User user1 = iUserRepository.findByUsername(user);
        var list = perfomanceRepository.findByUser(user1);
        List<String> listSubjectNames = list.stream().map(i -> i.getSubject().getSubject()).distinct().collect(Collectors.toList());
        log.info("PerfomanceService : findByUser");
        return listSubjectNames;
    }
}
