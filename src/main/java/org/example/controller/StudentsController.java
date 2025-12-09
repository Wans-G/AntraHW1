package org.example.controller;

import org.example.dto.TeacherDTO;
import org.example.entity.Teacher;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<TeacherDTO>> getTeacherById(@PathVariable Long studentId){
        List<Teacher> teacherList = studentService.findStudentById(studentId);
        List<TeacherDTO> teacherDTOList = teacherList.stream()
                .map(teacher -> new TeacherDTO(teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName()))
                .toList();
        return ResponseEntity.ok(teacherDTOList);
    }

}
