package com.example.students.services;

import com.example.students.model.User;
import com.example.students.repositories.IUserRepository;
import com.example.students.services.serviceInterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository studentRepository) {
        this.userRepository = studentRepository;
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }


    @Override
    public List<User> findByUserMiddleName(String MiddleName) {
        return this.userRepository.findByUserMiddleName(MiddleName);
    }

    @Override
    public User findByUserName(String UserName) {
        return this.userRepository.findByUserName(UserName);
    }

    @Override
    public <S extends User> S save(S s) {
        return this.userRepository.save(s);
    }
}
