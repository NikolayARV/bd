package com.example.ru.hogwarts.school.dto;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
import com.example.ru.hogwarts.school.model.Faculty;
import com.example.ru.hogwarts.school.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private int age;
    private FacultyDTO facultyDTO;
    private Long facultyId;
    FacultyRepository facultyRepository;

    public StudentDTO(String name, int age, long facultyId, FacultyDTO facultyDTO) {
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
        this.facultyDTO = facultyDTO;
    }

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyDTO(FacultyDTO.fromFaculty(student.getFaculty()));
        dto.setFacultyId(student.getFaculty().getId());

        return dto;
    }



    public Student toStudent() {
        Student student = new Student();
        //student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        student.setFaculty(this.getFacultyDTO().toFaculty());
        return student;
    }
}
