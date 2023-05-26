package com.example.ru.hogwarts.school.dto;

import com.example.ru.hogwarts.school.Repository.FacultyRepository;
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

    public static StudentDTO fromStudent(Optional<Student> student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.get().getId());
        dto.setName(student.get().getName());
        dto.setAge(student.get().getAge());
        dto.setFacultyDTO(FacultyDTO.fromFaculty(student.get().getFaculty()));
        dto.setFacultyId(student.get().getFaculty().getId());

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
