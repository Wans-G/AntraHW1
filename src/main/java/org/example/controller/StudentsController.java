package org.example.controller;

import org.example.dto.StudentDTO;
import org.example.dto.StudentRequest;
import org.example.dto.TeacherDTO;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}/teacher")
    public ResponseEntity<List<TeacherDTO>> getTeacherByStudentId(@PathVariable Long studentId){
        List<Teacher> teacherList = studentService.findTeacherByStudentId(studentId);
        List<TeacherDTO> teacherDTOList = teacherList.stream()
                .map(teacher -> new TeacherDTO(teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName()))
                .toList();
        return ResponseEntity.ok(teacherDTOList);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long studentId){
        Student student = studentService.findStudentByStudentId(studentId);
        StudentDTO studentDTO = studentService.toDto(student);
        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentRequest studentRequest){
        Student student = studentService.createStudent(studentRequest.getFirstName(), studentRequest.getLastName());
        StudentDTO studentDTO = studentService.toDto(student);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long studentId,
                                                    @RequestBody StudentRequest studentRequest){
        Student student = studentService.updateStudent(studentId, studentRequest.getFirstName(), studentRequest.getLastName());
        StudentDTO studentDTO = studentService.toDto(student);
        return ResponseEntity.ok(studentDTO);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudentById(studentId);
    }


}
/**
 *      - Get: /students/{id}
 *      - Post: /students/
 *      - Put: /students/{id}
 *      - Delete: /students/{id}
 * */