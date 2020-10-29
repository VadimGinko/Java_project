package com.example.students.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AcademicPerformance {
    public AcademicPerformance(Integer academicPerformanceId, Subject subject, User user, Integer mark, String discription) {
        this.academicPerformanceId = academicPerformanceId;
        this.subject = subject;
        this.user = user;
        this.mark = mark;
        this.discription = discription;
    }

    public AcademicPerformance(){
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer academicPerformanceId;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private User user;
    private Integer mark;
    private String discription;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
