package org.example.service;

import org.example.entity.StudentTeacher;
import org.example.entity.Teacher;
import org.example.repository.StudentTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentTeacherRepository studentTeacherRepository;
    @Autowired
    public StudentService(StudentTeacherRepository str){
        this.studentTeacherRepository = str;
    }
    public List<Teacher> findStudentById(Long studentId){
        List<StudentTeacher> res = this.studentTeacherRepository.findByStudent_StudentId(studentId);

        return res.stream()
                .map(StudentTeacher::getTeacher)
                .distinct()
                .collect(Collectors.toList());
    }
}
