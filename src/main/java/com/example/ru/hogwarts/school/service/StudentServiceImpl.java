package com.example.ru.hogwarts.school.service;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
import com.example.ru.hogwarts.school.Repository.StudentRepository;
import com.example.ru.hogwarts.school.dto.FacultyDTO;
import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

        return StudentDTO.fromStudent(studentRepository.findById(id));
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
        return makeSetDTOFromStudentsList(studentRepository.findByAge(age));

    }

    @Override
    public Set<StudentDTO> findStudentByAgeBetween(int age1, int age2) {

        return makeSetDTOFromStudentsList(studentRepository.findByAgeBetween(age1, age2));
    }

    @Override
    public Set<StudentDTO> findStudentsByFacultiId(long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId);
        return makeSetDTOFromStudentsList(faculty.getStudents());
    }

    @Override
    public Integer getStudentsCount() {
        return studentRepository.getStudentsCount();
    }

    @Override
    public Integer getAvgAge() {
        return studentRepository.getAvgAge();
    }

    @Override
    public Set<StudentDTO> findFiveYoungestStudents() {
        return makeSetDTOFromStudentsList(studentRepository.findFiveYoungestStudents());
    }

    public Set<StudentDTO> makeSetDTOFromStudentsList(Set<Student> studentList) {
        Set<StudentDTO> studentDTOS = new HashSet<>();
        for (Student student : studentList) {
            StudentDTO studentDTO = StudentDTO.fromStudent(Optional.of(student));
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public  List<StudentDTO> makeListDTOFromStudentsList(List<Student> studentList) {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : studentList) {
            StudentDTO studentDTO = StudentDTO.fromStudent(Optional.of(student));
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
    @Override
    public List<StudentDTO> findAllStudents(Integer pageNumber, Integer pageSize) {
        if (pageSize <= 0 || pageSize > 50) {
            pageSize = 50;
        }
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return makeListDTOFromStudentsList(studentRepository.findAll(pageRequest).getContent());

    }
}
