package com.amigoscode.springsecurity.students;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMINTRAINEE')")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        System.out.println("get all students");
        return STUDENTS;
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        System.out.printf("get student with id: %s", studentId);
        return STUDENTS
                .stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + "does not exist"));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(Student student) {
        System.out.println("register new student");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("delete student");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updatedStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println("update student");
        System.out.printf("%s %s%n", student, studentId);
    }
}
