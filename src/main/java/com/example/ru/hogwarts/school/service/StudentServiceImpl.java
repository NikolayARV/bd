package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
import com.example.ru.hogwarts.school.Repository.StudentRepository;
import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public StudentDTO createStudent(String name, int age, long facultyId) {
        FacultyDTO facultyDTO = FacultyDTO.fromFaculty(facultyRepository.getById(facultyId));
        StudentDTO studentDTO = new StudentDTO(name, age, facultyId, facultyDTO);
        Student student = studentDTO.toStudent();
       studentRepository.save(student);
       return studentDTO;
    }

    @Override
    public StudentDTO findStudent(long id) {

        return StudentDTO.fromStudent(studentRepository.getById(id));
    }


    @Override
    public StudentDTO editStudent(String name, int age, long facultyId, long id) {
        FacultyDTO facultyDTO = FacultyDTO.fromFaculty(facultyRepository.getById(facultyId));
        StudentDTO studentDTO = new StudentDTO(name, age, facultyId, facultyDTO);
        studentDTO.setId(id);
        Student student = studentDTO.toStudent();
        student.setId(id);
        studentRepository.save(student);
        return studentDTO;
    }

    @Override
    public void deleteStudent(long id) {

        studentRepository.deleteById(id);
    }

    @Override
    public Set<StudentDTO> findStudentByAge(int age) {
        return makeListDTOFromStudentsList(studentRepository.findByAge(age));

    }

    @Override
    public Set<StudentDTO> findStudentByAgeBetween(int age1, int age2) {

        return makeListDTOFromStudentsList(studentRepository.findByAgeBetween(age1, age2));
    }

    @Override
    public Set<StudentDTO> findStudentsByFacultiId(long facultyId) {
       Faculty faculty = facultyRepository.findById(facultyId);
     return   makeListDTOFromStudentsList(faculty.getStudents());
    }
    public Set<StudentDTO> makeListDTOFromStudentsList(Set<Student> studentList) {
        Set<StudentDTO> studentDTOS = new HashSet<>();
        for (Student student : studentList) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}
