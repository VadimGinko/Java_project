package com.example.students.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AcademicPerformance {
    public AcademicPerformance(){
    }

    public AcademicPerformance(Integer academicPerformanceId, Subject subject, String mark, Student student) {
        this.academicPerformanceId = academicPerformanceId;
        this.subject = subject;
        this.mark = mark;
        this.student = student;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer academicPerformanceId;
    @ManyToOne
    private Subject subject;
    private String mark;
    @ManyToOne
    private Student student;

    public Integer getAcademicPerformanceId() {
        return academicPerformanceId;
    }

    public void setAcademicPerformanceId(Integer academicPerformanceId) {
        this.academicPerformanceId = academicPerformanceId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
