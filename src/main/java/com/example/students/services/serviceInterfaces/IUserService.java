package com.example.students.services.serviceInterfaces;

import com.example.students.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    List<User> findByUserMiddleName(String MiddleName);
    User findByUserName(String MiddleName);
    <S extends User> S save(S s);
}
