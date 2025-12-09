package org.example.repository;

import org.example.entity.StudentTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Long> {
    List<StudentTeacher> findByStudent_StudentId(Long id);
}
