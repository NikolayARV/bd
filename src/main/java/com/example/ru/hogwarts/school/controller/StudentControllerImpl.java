package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.model.Student;
import com.example.ru.hogwarts.school.service.StudentService;
import org.aspectj.weaver.Position;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentControllerImpl {
    private final StudentService studentService;

    public StudentControllerImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public Optional<Student> getStudent(@PathVariable long id) {
        return studentService.findStudent(id);
    }


    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student, long id) {
        return studentService.editStudent(student, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sorted/{age}")
        public List<Student> getStudentByAge(@PathVariable int age) {

            return studentService.findStudentByAge(age);
        }
}
