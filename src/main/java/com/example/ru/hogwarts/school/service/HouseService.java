package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.model.Faculty;
import org.aspectj.weaver.Position;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    FacultyDTO createFaculty(FacultyDTO facultyDTO);

    Optional<FacultyDTO> findFaculty(long id);

    FacultyDTO editFaculty(FacultyDTO facultyDTO, long id);

    void deleteFaculty(long id);

    List<FacultyDTO> findFacultyByColor(String color);

    FacultyDTO findFacultyByName(String name);

    FacultyDTO findFacultyByStudentId(long studentId);
}
