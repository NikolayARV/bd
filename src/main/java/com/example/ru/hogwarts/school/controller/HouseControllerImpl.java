package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.service.HouseService;
import org.aspectj.weaver.Position;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class HouseControllerImpl implements HouseController {
    private final HouseService houseService;

    public HouseControllerImpl(HouseService houseService) {
        this.houseService = houseService;
    }

    @Override
    @GetMapping("{id}")
    public Optional<Faculty> getFaculty(@PathVariable long id) {
        return houseService.findFaculty(id);
    }

    @Override
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return houseService.createFaculty(faculty);
    }

    @Override
    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return houseService.editFaculty(faculty);
    }

    @Override
    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        houseService.deleteFaculty(id);
    }

    @Override
       @GetMapping("/sorted/{color}")
        public List<Faculty> getFacultyByColor(@PathVariable String color) {
            return houseService.findFacultyByColor(color);
        }
}
