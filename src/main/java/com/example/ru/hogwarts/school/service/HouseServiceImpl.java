package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
import com.example.ru.hogwarts.school.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseServiceImpl implements HouseService {

    private final FacultyRepository facultyRepository;


    public HouseServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty(long id) {

        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty, long id) {
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    @Override
        public List<Faculty> findFacultyByColor(String color) {
           return facultyRepository.findByColor(color);
       }
}
