package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.model.Faculty;
import org.aspectj.weaver.Position;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface HouseController {

    @GetMapping("{id}")
    Optional<Faculty> getFaculty(@PathVariable long id);

    @PostMapping
    Faculty createFaculty(Faculty faculty);

    @PutMapping
    Faculty updateFaculty(Faculty faculty);

    @DeleteMapping("{id}")
    void deleteFaculty(@PathVariable long id);

    @GetMapping("{color}")
        List<Faculty> getFacultyByColor(@PathVariable String color);
}
