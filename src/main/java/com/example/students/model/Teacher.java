package com.example.students.model;

import javax.persistence.*;

@Entity
public class Teacher {
    public Teacher(){

    }

    public Teacher(Integer teacherId, String teacherName, String teacherSurname, String teacherMiddleName, Subject subject) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.teacherMiddleName = teacherMiddleName;
        this.subject = subject;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer teacherId;
    private String teacherName;
    private String teacherSurname;
    private String teacherMiddleName;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }

    public String getTeacherMiddleName() {
        return teacherMiddleName;
    }

    public void setTeacherMiddleName(String teacherMiddleName) {
        this.teacherMiddleName = teacherMiddleName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToOne
    private Subject subject;
}
