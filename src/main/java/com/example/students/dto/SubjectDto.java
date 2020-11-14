package com.example.students.dto;

import com.example.students.model.Subject;
import com.example.students.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDto {
    private List<String> subject;

    public static SubjectDto fromUser(User user) {
        SubjectDto subjectDto = new SubjectDto();
        List<String> subjects = new ArrayList<>();
        for(var i: user.getSubjects()){
            subjects.add(i.getSubject());
        }
        subjectDto.setSubject(subjects);
        return subjectDto;
    }
}