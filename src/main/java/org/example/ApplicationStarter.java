/**
 * HW1 Requirements:
 *
 * 1. Need a db(derby) to record teacher, student information(many-to-many relationship)
 *   a. Construct 3 tables(student entity, student-teacher entity, teacher entity)
 *   b. To keep it simple, for the entity, we only have id(unique primary key), first_name, last_name
 * 2. Use SpringMVC(Controller, Service...) to create an endpoint for user:
 *   a. get all related teachers by student id
 *      - Endpoint Design: /students/{id}/teachers
 *   b. CRUD for student
 *      - Get: /students/{id}
 *      - Post: /students/
 *      - Put: /students/{id}
 *      - Delete: /students/{id}
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

            // new students
            Student s1 = studentRepo.save(new Student("San", "Zhang"));
            Student s2 = studentRepo.save(new Student("Si", "Li"));

            // new teachers
            Teacher t1 = teacherRepo.save(new Teacher("Wu", "Wang"));
            Teacher t2 = teacherRepo.save(new Teacher("Liu", "Zhao"));

            // connect s1->t1,t2   s2->t2
            stRepo.save(new StudentTeacher(s1, t1));
            stRepo.save(new StudentTeacher(s1, t2));
            stRepo.save(new StudentTeacher(s2, t2));

            System.out.println("=== Seed Done ===");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}