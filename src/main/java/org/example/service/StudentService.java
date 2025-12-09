package org.example.service;

import org.example.dto.StudentDTO;
import org.example.entity.Student;
import org.example.entity.StudentTeacher;
import org.example.entity.Teacher;
import org.example.repository.StudentRepository;
import org.example.repository.StudentTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentTeacherRepository studentTeacherRepository;
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentTeacherRepository str, StudentRepository sr){
        this.studentTeacherRepository = str;
        this.studentRepository = sr;
    }

    public StudentDTO toDto(Student student){
        return new StudentDTO(student.getStudentId(), student.getFirstName(), student.getLastName());
    }
    public List<Teacher> findTeacherByStudentId(Long studentId){
        //check if student exist
        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));

        List<StudentTeacher> res = studentTeacherRepository.findByStudent_StudentId(studentId);

        return res.stream()
                .map(StudentTeacher::getTeacher)
                .distinct()
                .collect(Collectors.toList());
    }

    public Student findStudentByStudentId(Long studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));
    }

    public Student createStudent(String firstName, String lastName){
        Student student = new Student(firstName, lastName);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long studentId, String firstName, String lastName){
        Student existingStudent = findStudentByStudentId(studentId);
        existingStudent.setFirstName(firstName);
        existingStudent.setLastName(lastName);
        Student updatedStudent = studentRepository.save(existingStudent);
        return updatedStudent;
    }

    public void deleteStudentById(Long studentId){
        Student existingStudent = findStudentByStudentId(studentId);
        //remove junction table first
        List<StudentTeacher> links = studentTeacherRepository.findByStudent_StudentId(studentId);
        studentTeacherRepository.deleteAll(links);
        //remove the student last
        studentRepository.delete(existingStudent);
    }

}
