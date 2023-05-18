package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.model.Faculty;
import org.aspectj.weaver.Position;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    Faculty createFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(long id);

    Faculty editFaculty(Faculty faculty, long id);

    void deleteFaculty(long id);

    List<Faculty> findFacultyByColor(String color);
}
