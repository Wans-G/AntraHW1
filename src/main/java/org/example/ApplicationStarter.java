/**
 * 1. Need a db(derby) to record teacher, student information(many to many)
 *   a. Construct 3 tables(student entity, student_teacher entity, teacher entity)
 *   b. To keep it simple, for the entity, we only have id(unique primary key), first_name, last_name, and foreign keys
 * 2. Use SpringMVC(Controller, Service...) to create an endpoint for user:
 *   a. get all related teachers by student id
 *      - Endpoint Design: /students?id={id}/
 *
 * Comments:adding derbyshared.jar along with derby.jar and derbytools.jar to the dependencies
 * */


package org.example;

import org.example.entity.Student;
import org.example.entity.StudentTeacher;
import org.example.entity.Teacher;
import org.example.repository.StudentRepository;
import org.example.repository.StudentTeacherRepository;
import org.example.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ApplicationStarter {

    @Bean
    CommandLineRunner initData(StudentRepository studentRepo,
                               TeacherRepository teacherRepo,
                               StudentTeacherRepository stRepo) {

        return args -> {
            System.out.println("=== Seeding Derby Database ===");

            // 创建几个学生
            Student s1 = studentRepo.save(new Student("Alice", "Wang"));
            Student s2 = studentRepo.save(new Student("Bob", "Li"));

            // 创建几个老师
            Teacher t1 = teacherRepo.save(new Teacher("Tom", "Zhang"));
            Teacher t2 = teacherRepo.save(new Teacher("Jerry", "Liu"));

            // 建立关系
            stRepo.save(new StudentTeacher(s1, t1));
            stRepo.save(new StudentTeacher(s1, t2));
            stRepo.save(new StudentTeacher(s2, t2));

            System.out.println("=== Seed Done ===");
        };
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        SpringApplication.run(ApplicationStarter.class, args);
    }
}