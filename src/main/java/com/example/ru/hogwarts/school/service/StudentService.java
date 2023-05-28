package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Student;
import org.aspectj.weaver.Position;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentService {



    StudentDTO createStudent(String name, int age, long faculityId);

    StudentDTO findStudent(long id);

    StudentDTO editStudent(String name, int age, long facultyId, long id);

    void deleteStudent(long id);

    Set<StudentDTO> findStudentByAge(int age);

    Set<StudentDTO> findStudentByAgeBetween(int age1, int age2);

    Set<StudentDTO> findStudentsByFacultiId(long facultyId);

    Integer getStudentsCount();

    Integer getAvgAge();

    Set<StudentDTO> findFiveYoungestStudents();
    List<StudentDTO> findAllStudents(Integer pageNumber, Integer pageSize);
}
