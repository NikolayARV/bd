package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student createStudent(Student student);

    Optional<Student> findStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);


    List<Student> findStudentByAge(int age);
}
