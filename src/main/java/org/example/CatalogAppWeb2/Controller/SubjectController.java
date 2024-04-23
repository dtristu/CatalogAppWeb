package org.example.CatalogAppWeb2.Controller;

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
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects()
    {
    List<Subject> subjects=subjectService.getSubjects();
    if (subjects.isEmpty())
    {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
    return new ResponseEntity<List<Subject>>(subjects,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Student>> getStudentsBySubject(@PathVariable int id)
    {
      List<Student> students=subjectService.getStudentsBySubject(id);
        if (students.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
    }

    @GetMapping(value = "/{subjectId}/{studentId}")
    public ResponseEntity<List<Grade>> getGradesBySubjectAndStudent(@PathVariable int subjectId, @PathVariable int studentId)
    {
        List<Grade> grades=subjectService.getGradesBySubjectAndStudent(subjectId,studentId);
        if (grades.isEmpty())
        {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
        return new ResponseEntity<List<Grade>>(grades,HttpStatus.OK);
    }
    @PutMapping(consumes = "application/json")
    public ResponseEntity<SubjectDTO> putSubject(@RequestBody SubjectDTO s)
    {
        Optional<SubjectDTO> o=subjectService.putSubject(s);
        if (o.isPresent())
        {return new ResponseEntity<SubjectDTO>(o.get(),HttpStatus.OK);}
        else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}

    }
    }

