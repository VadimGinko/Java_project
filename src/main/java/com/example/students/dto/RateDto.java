package com.example.students.dto;

import com.example.students.model.Subject;
import com.example.students.model.User;
import lombok.Data;

@Data
public class RateDto {
    private String username;
    private String subject;
    private Integer mark;
    private String description;
}
