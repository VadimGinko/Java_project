package com.example.students.controller;

import com.example.students.model.Student;
import com.example.students.model.Subject;
import com.example.students.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class IStudentsController {
    public IStudentsController(com.example.students.services.SubjectService subjectService, StudentService studentService) {
        this.SubjectService = subjectService;
        this.studentService = studentService;
    }

    private com.example.students.services.SubjectService SubjectService;
    private com.example.students.services.StudentService studentService;
    private static List<Subject> albums = new ArrayList<>();

    static {
        // albums.add(new Student("Full Stack Development with JHipster", "Deepu K Sasidharan, Sendil Kumar"));
        // albums.add(new Student("Pro Spring Security", "Carlo Scarioni, Massimo Nardone"));
    }

    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    //@GetMapping(value = {"/", "index"})
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called(GET)");
        return modelAndView;
    }

    @GetMapping(value = {"/allalbums"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        List<Student> albums = this.studentService.findByStudentMiddleName("vadim");
        model.addAttribute("albums", albums);
        log.info("/allalbums was called(GET)");
        return modelAndView;
    }
}