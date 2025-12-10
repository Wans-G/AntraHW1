package org.example.service;

import org.example.dto.StudentDTO;
import org.example.entity.Student;
import org.example.entity.Teacher;

import java.util.List;

public interface StudentService {

    public StudentDTO toDto(Student student);

    public List<Teacher> findTeacherByStudentId(Long studentId);

    public Student findStudentByStudentId(Long studentId);

    public Student createStudent(String firstName, String lastName);

    public Student updateStudent(Long studentId, String firstName, String lastName);

    public void deleteStudentById(Long studentId);
}
