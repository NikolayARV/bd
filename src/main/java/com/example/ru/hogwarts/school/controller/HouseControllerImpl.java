package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.service.HouseService;
import org.aspectj.weaver.Position;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class HouseControllerImpl  {
    private final HouseService houseService;

    public HouseControllerImpl(HouseService houseService) {
        this.houseService = houseService;
    }


    @GetMapping("{id}")
    public Optional<Faculty> getFaculty(@PathVariable long id) {
        return houseService.findFaculty(id);
    }


    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return houseService.createFaculty(faculty);
    }


    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty, long id) {

        return houseService.editFaculty(faculty, id);
    }


    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        houseService.deleteFaculty(id);
    }


       @GetMapping("/sorted")
        public List<Faculty> getFacultyByColor(@RequestParam String color) {
            return houseService.findFacultyByColor(color);
        }
}
