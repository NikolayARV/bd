package com.example.ru.hogwarts.school.Repository;

import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(Integer position);
}
