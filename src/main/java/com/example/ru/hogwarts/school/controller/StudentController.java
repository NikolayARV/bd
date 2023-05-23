package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Student;
import com.example.ru.hogwarts.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public StudentDTO getStudent(@PathVariable long id) {

        return studentService.findStudent(id);
    }


    @PostMapping
    public StudentDTO createStudent(@RequestParam String name, int age, long faculityId) {
        return studentService.createStudent(name, age, faculityId);
    }

    @PutMapping
    public StudentDTO updateStudent(@RequestParam String name, int age, long faculityId, long id) {
        return studentService.editStudent(name, age, faculityId, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity getStudentByAge(@RequestParam(required = false) Integer age1,
                                          @RequestParam(required = false) Integer age2) {

        if (age1 >= 0 && age2 > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(age1, age2));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sorted/{age}")
    public ResponseEntity getStudentByAge(@RequestParam(required = false) Integer age) {

        if (age >= 0) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity getStudentsByFacultyId(@RequestParam long facultyId) {
        if (facultyId > 0) {
            return ResponseEntity.ok(studentService.findStudentsByFacultiId(facultyId));
        }
        return ResponseEntity.notFound().build();
    }


}
