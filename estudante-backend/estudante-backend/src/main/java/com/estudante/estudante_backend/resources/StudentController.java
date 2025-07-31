package com.estudante.estudante_backend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudante.estudante_backend.models.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
public class StudentController {
    private List<Student> students = new ArrayList<>();

    StudentController() {
        students.add(new Student(1, "Ana Silva", "ana.silva@email.com", "11999999999", 1, "Manhã"));
        students.add(new Student(2, "Bruno Souza", "bruno.souza@email.com", "11988888888", 2, "Noite"));
        students.add(new Student(3, "Carla Pereira", "carla.pereira@email.com", "11977777777", 3, "Manhã"));
    }


    @GetMapping("students")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudents(@PathVariable int id) {
        Student stud = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
        return ResponseEntity.ok(stud);
    }

    @PostMapping("students")
    public ResponseEntity<Student> save(@RequestBody Student student) {

        student.setId(students.size() + 1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(student.getId())
                .toUri();
        return ResponseEntity.created(location).body(student);
    }

}
