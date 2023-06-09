package com.example.ru.hogwarts.school.controller;

import com.example.ru.hogwarts.school.dto.StudentDTO;
import com.example.ru.hogwarts.school.model.Avatar;
import com.example.ru.hogwarts.school.model.Student;
import com.example.ru.hogwarts.school.service.AvatarSevice;
import com.example.ru.hogwarts.school.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarSevice avatarSevice;

    public StudentController(StudentService studentService, AvatarSevice avatarSevice) {

        this.studentService = studentService;
        this.avatarSevice = avatarSevice;
    }

    @GetMapping("{id}")
    public StudentDTO getStudent(@PathVariable long id) {

        return studentService.findStudent(id);
    }

    @GetMapping("/count")
    public Integer getStudentCount() {
        return studentService.getStudentsCount();
    }
    @GetMapping("/avgAge")
    public Integer getAvgAge() {
        return studentService.getAvgAge();
    }

    @GetMapping("/youngest-5")
    public Set<StudentDTO> findFiveYoungestStudents() {
        return studentService.findFiveYoungestStudents();
    }
    @PostMapping
    public StudentDTO createStudent(@RequestParam String name, int age, long faculityId) {
        return studentService.createStudent(name, age, faculityId);
    }

    @PutMapping
    public StudentDTO updateStudent(@RequestParam String name, int age, long faculityId, long id) {
        return studentService.editStudent(name, age, faculityId, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity getStudentByAge(@RequestParam(required = false) Integer age1,
                                          @RequestParam(required = false) Integer age2) {

        if (age1 >= 0 && age2 > 0) {
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(age1, age2));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sorted/{age}")
    public ResponseEntity getStudentByAge(@RequestParam(required = false) Integer age) {

        if (age >= 0) {
            return ResponseEntity.ok(studentService.findStudentByAge(age));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity getStudentsByFacultyId(@RequestParam long facultyId) {
        if (facultyId > 0) {
            return ResponseEntity.ok(studentService.findStudentsByFacultiId(facultyId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarSevice.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarSevice.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getPreview().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<StudentDTO> studentsDTOs = studentService.findAllStudents(pageNumber, pageSize);
        return ResponseEntity.ok(studentsDTOs);
    }
    @GetMapping(value = "/{id}/avatar")
    public void downLoadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarSevice.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}