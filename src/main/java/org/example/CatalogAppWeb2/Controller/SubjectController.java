package org.example.CatalogAppWeb2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.SubjectDTO;
import org.example.CatalogAppWeb2.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/subject",
        produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
@Tag(name = "Subject API")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Operation(summary = "Gets all Subjects", description = "Returns all Subjects from the database")
    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects()
    {
    List<Subject> subjects=subjectService.getSubjects();
    if (subjects.isEmpty())
    {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
    return new ResponseEntity<List<Subject>>(subjects,HttpStatus.OK);
    }

    @Operation(summary = "Gets Students by Subject Id", description = "Returns all Students registered at a given Subject (Subject Id)")
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Student>> getStudentsBySubject(@PathVariable @Parameter(name = "id", description = "Subject id", example = "1") int id)
    {
      List<Student> students=subjectService.getStudentsBySubject(id);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
    }

    @Operation(summary = "Gets Grades by Subject Id and Student Id", description = "Returns all Grades given a Student Id and Subject Id")
    @GetMapping(value = "/{subjectId}/{studentId}")
    public ResponseEntity<List<Grade>> getGradesBySubjectAndStudent(
            @PathVariable @Parameter(name = "id", description = "Subject id", example = "1") int subjectId,
            @PathVariable @Parameter(name = "id", description = "Student id", example = "1") int studentId)
    {
        List<Grade> grades=subjectService.getGradesBySubjectAndStudent(subjectId,studentId);
        if (grades.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<List<Grade>>(grades,HttpStatus.OK);
    }

    @Operation(summary = "Puts a Subject", description = "Puts a Subject with the corresponding details")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<SubjectDTO> putSubject(@RequestBody SubjectDTO s)
    {
        Optional<SubjectDTO> o=subjectService.putSubject(s);
        if (o.isPresent())
        {return new ResponseEntity<SubjectDTO>(o.get(),HttpStatus.OK);}
        else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}

    }
    }

