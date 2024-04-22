package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/student",
        produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping(value = "/topstudents/{subjectId}")
    public ResponseEntity<List<Student>> getTopStudentsBySubject(@PathVariable int subjectId){
        List<Student> students=studentService.getTopStudents(subjectId);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
    }

}
