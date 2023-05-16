package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.Repository.StudentRepository;
import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public Optional<Student> findStudent(long id) {

        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {

        studentRepository.deleteById(id);
    }

    @Override
        public List<Student> findStudentByAge(int age) {
            return studentRepository.findByAge(age);
       }
}
