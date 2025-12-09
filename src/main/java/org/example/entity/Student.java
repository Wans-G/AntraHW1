//package org.example.entity;
//
//import jakarta.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "students")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    // 一个学生对应多个 student_teacher 记录
//    @OneToMany(mappedBy = "student")
//    private List<StudentTeacher> studentTeachers = new ArrayList<>();
//
//    public Student() {
//    }
//
//    public Student(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
//
//    // getter / setter 省略，也可以用 Lombok @Data/@Getter/@Setter
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) { this.id = id; }
//
//    public String getFirstName() { return firstName; }
//
//    public void setFirstName(String firstName) { this.firstName = firstName; }
//
//    public String getLastName() { return lastName; }
//
//    public void setLastName(String lastName) { this.lastName = lastName; }
//
//    public List<StudentTeacher> getStudentTeachers() { return studentTeachers; }
//
//    public void setStudentTeachers(List<StudentTeacher> studentTeachers) {
//        this.studentTeachers = studentTeachers;
//    }
//}



package org.example.entity;

import jakarta.persistence.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id //mark as PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db will automically ++id
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name") //convert java naming to sql naming
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "student")
    private List<StudentTeacher> studentTeachers = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<StudentTeacher> getStudentTeachers() {
        return studentTeachers;
    }

    public void setStudentTeachers(List<StudentTeacher> studentTeachers) {
        this.studentTeachers = studentTeachers;
    }

}
