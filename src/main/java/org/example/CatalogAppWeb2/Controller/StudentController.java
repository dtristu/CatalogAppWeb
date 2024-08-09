package org.example.CatalogAppWeb2.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.example.CatalogAppWeb2.DAO.Grade;
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
import java.util.Set;

@RestController
@RequestMapping(path = "/student",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Student API")
public class StudentController {
    @Autowired
    StudentService studentService;
    ValidatorFactory factoryValidator = Validation.buildDefaultValidatorFactory();
    jakarta.validation.Validator validator = factoryValidator.getValidator();

    @Operation(summary = "Get top Students by subjectId", description = "Returns top Students at a corresponding " +
            "subject (given the id of the subject)", responses = {

            @ApiResponse(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Students not found"),})
    @GetMapping(value = "/topstudents/{subjectId}")
    public ResponseEntity<List<Student>> getTopStudentsBySubject( @Parameter(name = "subjectId", description =
            "Subject id", example = "1") @PathVariable("subjectId") int subjectId) {
        List<Student> students = studentService.getTopStudents(subjectId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Get top Students", description = "Returns top Students at all Subjects", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Students not found"),})
    @GetMapping(value = "/topstudents")
    public ResponseEntity<List<Student>> getTopStudents() {
        List<Student> students = studentService.getTopStudents();
        if (students.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Operation(summary = "Puts a Student", description = "Puts a Student with the corresponding details")
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)))
            @ApiResponse(responseCode = "404", description = "Students not found")
            @ApiResponse(responseCode = "406", description = "Student not valid")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Student> putStudent(@Parameter(description = "A StudentDTO  object", required = true,
            content = @Content(
                    schema = @Schema(implementation = StudentDTO.class))) @org.springframework.web.bind.annotation.RequestBody StudentDTO s) {
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(s);
        if (!violations.isEmpty()){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        Optional<Student> o = studentService.putStudent(s);
        if (o.isPresent()) {
            return new ResponseEntity<Student>(o.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Gets failing Students", description = "Returns Students with an average grade lower than 5 at " +
            "a given Subject", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Students not found")})
    @GetMapping(value = "/failingstudents/{subjectId}")
    public ResponseEntity<List<Student>> getFailingStudentsBySubjectId(@Parameter(name = "subjectId", description =
            "Subject id", example = "1") @PathVariable int subjectId) {
        List<Student> students = studentService.getFailingStudents(subjectId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
