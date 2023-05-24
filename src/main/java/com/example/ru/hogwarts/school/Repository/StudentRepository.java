package com.example.ru.hogwarts.school.Repository;

import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Set<Student> findByAge(Integer age);
    Set<Student> findByAgeBetween(int age, int age2);

}
