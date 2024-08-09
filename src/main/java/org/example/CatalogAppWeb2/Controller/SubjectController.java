package org.example.CatalogAppWeb2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
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
import java.util.Set;

@RestController
@RequestMapping(path = "/subject",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Subject API")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @Operation(summary = "Gets all Subjects", description = "Returns all Subjects from the database", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Subject.class))),
            @ApiResponse(responseCode = "404", description = "Subjects not found")})
    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
    }

    @Operation(summary = "Gets Students by Subject Id", description = "Returns all Students registered at a given" +
            " Subject (Subject Id)")
    @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
    @ApiResponse(responseCode = "404", description = "Students not found")
    @GetMapping(value = "/{subjectId}")
    public ResponseEntity<List<Student>> getStudentsBySubject( @Parameter(name = "subjectId", description =
            "Subject id", example = "1") @PathVariable("subjectId") int subjectId) {
        List<Student> students = subjectService.getStudentsBySubject(subjectId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    @Operation(summary = "Gets Grades by Subject Id and Student Id", description = "Returns all Grades given a" +
            " Student Id and Subject Id")
    @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grade.class)))
    @ApiResponse(responseCode = "404", description = "Students not found")
    @GetMapping(value = "/{subjectId}/{studentId}")
    public ResponseEntity<List<Grade>> getGradesBySubjectAndStudent(
            @Parameter(name = "subjectId", required = true,example = "1") @PathVariable("subjectId") int subjectId,
             @Parameter(name ="studentId", required = true, example = "1") @PathVariable("studentId") int studentId) {
        List<Grade> grades = subjectService.getGradesBySubjectAndStudent(subjectId, studentId);
        if (grades.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Grade>>(grades, HttpStatus.OK);
    }

    @Operation(summary = "Puts a Subject", description = "Puts a Subject with the corresponding details")
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubjectDTO.class)))
            @ApiResponse(responseCode = "404", description = "Subject not found")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<SubjectDTO> putSubject(@Parameter(name = "SubjectDTO",description = "Create subject",
            schema = @Schema(implementation = SubjectDTO.class)) @Valid @RequestBody SubjectDTO s) {
        Optional<SubjectDTO> o = subjectService.putSubject(s);
        if (o.isPresent()) {
            return new ResponseEntity<SubjectDTO>(o.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}

