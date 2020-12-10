package com.example.students.repositories;

import com.example.students.model.Faculty;
import com.example.students.model.Role;
import com.example.students.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findByMiddleName(String MiddleName);
    User findByUsername(String UserName);
    User findByEmail(String UserName);
    List<User> findByUserCourseAndFacultyNameAndUserGroup(Integer userCourse, Faculty facultyName, Integer userGroup);
    <S extends User> S save(S s);
}