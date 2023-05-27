package com.example.ru.hogwarts.school.Repository;

import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Set<Student> findByAge(Integer age);

    Set<Student> findByAgeBetween(int age, int age2);

    @Query(value = "SELECT COUNT (*) FROM STUDENT", nativeQuery = true)
    Integer getStudentsCount();

    @Query(value = "SELECT AVG(age) FROM STUDENT", nativeQuery = true)
    Integer getAvgAge();
    @Query(value = "SELECT * FROM STUDENT ORDER BY age LIMIT 5", nativeQuery = true)
    Set<Student> findFiveYoungestStudents();
}
