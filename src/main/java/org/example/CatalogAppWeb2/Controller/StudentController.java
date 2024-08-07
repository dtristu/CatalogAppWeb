package org.example.CatalogAppWeb2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Student API")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Operation(summary = "Get top Students by subjectId", description = "Returns top Students at a corresponding subject (given the id of the subject)")
    @GetMapping(value = "/topstudents/{subjectId}")
    public ResponseEntity<List<Student>> getTopStudentsBySubject(@PathVariable @Parameter(name = "id", description = "Subject id", example = "1") int subjectId){
        List<Student> students=studentService.getTopStudents(subjectId);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Get top Students", description = "Returns top Students at all Subjects")
    @GetMapping(value = "/topstudents")
    public ResponseEntity<List<Student>> getTopStudents(){
        List<Student> students=studentService.getTopStudents();
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Puts a Student", description = "Puts a Student with the corresponding details")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Student> putStudent(@RequestBody StudentDTO s)
    {
        Optional<Student> o=studentService.putStudent(s);
        if (o.isPresent())
        {return new ResponseEntity<Student>(o.get(),HttpStatus.OK);}
        else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
    }

    @Operation(summary = "Gets failing Students", description = "Returns Students with an average grade lower than 5 at a given Subject")
    @GetMapping(value = "/failingstudents/{subjectId}")
    public ResponseEntity<List<Student>> getFailingStudentsBySubjectId(@PathVariable @Parameter(name = "id", description = "Subject id", example = "1") int subjectId){
        List<Student> students=studentService.getFailingStudents(subjectId);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
