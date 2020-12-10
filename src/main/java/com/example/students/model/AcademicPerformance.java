package com.example.students.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AcademicPerformance extends BaseEntity{
    public AcademicPerformance(Integer academicPerformanceId, Subject subject, User user, Integer mark, String discription) {
        this.subject = subject;
        this.user = user;
        this.mark = mark;
        this.discription = discription;
    }

    public AcademicPerformance(){
    }


    @ManyToOne
    private Subject subject;
    @ManyToOne
    private User user;
    private Integer mark;
    private String discription;

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
