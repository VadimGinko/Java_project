package com.example.students.model;

import javax.persistence.*;


@Entity
public class TeacherToGroup {
    public TeacherToGroup(Integer teacherToGroupId, Faculty faculty, Integer course, Integer tgroup, User user, Subject subject) {
        this.teacherToGroupId = teacherToGroupId;
        this.faculty = faculty;
        this.course = course;
        this.tgroup = tgroup;
        this.user = user;
        this.subject = subject;
    }

    public TeacherToGroup() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer teacherToGroupId;
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

    public Integer getTeacherToGroupId() {
        return teacherToGroupId;
    }

    public void setTeacherToGroupId(Integer teacherToGroupId) {
        this.teacherToGroupId = teacherToGroupId;
    }

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
