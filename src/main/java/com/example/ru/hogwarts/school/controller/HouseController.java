package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }


    @GetMapping("{id}")
    public Optional<FacultyDTO> getFaculty(@PathVariable long id) {
        return houseService.findFaculty(id);
    }


    @PostMapping
    public FacultyDTO createFaculty(@RequestBody FacultyDTO facultyDTO) {
        return houseService.createFaculty(facultyDTO);
    }


    @PutMapping
    public FacultyDTO updateFaculty(@RequestBody FacultyDTO facultyDTO, long id) {

        return houseService.editFaculty(facultyDTO, id);
    }


    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        houseService.deleteFaculty(id);
    }


    @GetMapping("/sorted")
    public List<FacultyDTO> getFacultyByColor(@RequestParam String color) {

        return houseService.findFacultyByColor(color);
    }

    @GetMapping("/sorted/name")
    public FacultyDTO getFacultyByName(@RequestParam String name) {

        return houseService.findFacultyByName(name);
    }

    @GetMapping("/sorted/student")
    public FacultyDTO getFacultyByStudentId(@RequestParam long studentId) {
        return houseService.findFacultyByStudentId(studentId);
    }
}

