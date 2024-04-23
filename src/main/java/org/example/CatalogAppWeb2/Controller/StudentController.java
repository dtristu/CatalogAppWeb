package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DTO.StudentDTO;
import org.example.CatalogAppWeb2.DTO.SubjectDTO;
import org.example.CatalogAppWeb2.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping(value = "/topstudents")
    public ResponseEntity<List<Student>> getTopStudents(){
        List<Student> students=studentService.getTopStudents();
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Student> putStudent(@RequestBody StudentDTO s)
    {
        Optional<Student> o=studentService.putStudent(s);
        if (o.isPresent())
        {return new ResponseEntity<Student>(o.get(),HttpStatus.OK);}
        else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
    }
    @GetMapping(value = "/failingstudents/{subjectId}")
    public ResponseEntity<List<Student>> getFailingStudentsBySubjectId(@PathVariable int subjectId){
        List<Student> students=studentService.getFailingStudents(subjectId);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
