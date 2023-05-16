package com.example.ru.hogwarts.school.Repository;

import com.example.ru.hogwarts.school.model.Faculty;
import org.aspectj.weaver.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String position);
}
