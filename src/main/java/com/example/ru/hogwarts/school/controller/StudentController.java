package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface StudentController {
    @GetMapping("{id}")
    Optional<Student> getStudent(@PathVariable long id);

    @PostMapping
    Student createStudent(Student student);

    @PutMapping
    Student updateStudent(Student student);

    @DeleteMapping("{id}")
    ResponseEntity deleteStudent(@PathVariable long id);

   @GetMapping("{age}")
      List<Student> getStudentByAge(@PathVariable int age);
}
