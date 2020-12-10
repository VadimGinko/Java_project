package com.example.students.model;

import javax.persistence.*;


@Entity
public class TeacherToGroup extends BaseEntity{
    public TeacherToGroup(Faculty faculty, Integer course, Integer tgroup, User user, Subject subject) {
        this.faculty = faculty;
        this.course = course;
        this.tgroup = tgroup;
        this.user = user;
        this.subject = subject;
    }

    public TeacherToGroup() {
    }

    @ManyToOne
    private Faculty faculty;
    @Column(name = "Course")
    private Integer course;
    @Column(name = "Tgroup")
    private Integer tgroup;
    @ManyToOne
    private User user;
    @ManyToOne
    private Subject subject;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getGroup() {
        return tgroup;
    }

    public void setGroup(Integer tgroup) {
        this.tgroup = tgroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
