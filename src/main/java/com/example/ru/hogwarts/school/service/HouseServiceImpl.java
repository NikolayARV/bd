package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
import com.example.ru.hogwarts.school.Repository.StudentRepository;
import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HouseServiceImpl implements HouseService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public HouseServiceImpl(FacultyRepository facultyRepository, StudentRepository studentRepository, StudentService studentService) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    @Override
    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        facultyRepository.save(facultyDTO.toFaculty());
        return facultyDTO;
    }

    @Override
    public Optional<FacultyDTO> findFaculty(long id) {
        return Optional.of(FacultyDTO.fromFaculty(facultyRepository.findById(id)));

    }

    @Override
    public FacultyDTO editFaculty(FacultyDTO facultyDTO, long id) {

        facultyDTO.setId(id);
        facultyRepository.save(facultyDTO.toFaculty());
        return facultyDTO;
    }

    @Override
    public void deleteFaculty(long id) {
      Set<Student> students= facultyRepository.getById(id).getStudents();
        studentRepository.deleteAll(students);

        facultyRepository.deleteById(id);
    }

    @Override
    public List<FacultyDTO> findFacultyByColor(String color) {
        List<Faculty> facultyList = facultyRepository.findByColor(color);
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        for (Faculty faculty : facultyList) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
            facultyDTOS.add(facultyDTO);
        }
        return facultyDTOS;
    }

    @Override
    public FacultyDTO findFacultyByName(String name) {
        return FacultyDTO.fromFaculty(facultyRepository.findByNameIgnoreCase(name));
    }

    @Override
    public FacultyDTO findFacultyByStudentId(long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        return FacultyDTO.fromFaculty(student.get().getFaculty());


    }
}
