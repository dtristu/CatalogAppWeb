package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.StudentDTO;
import org.example.CatalogAppWeb2.Service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    StudentService studentService;
    @InjectMocks
    StudentController studentController;

    List<Student> studentList;
    ResponseEntity<List<Student>> listResponseEntity;
    Student student;
    StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        studentList = new ArrayList<>();
        student = new Student("abc", 1, new HashSet<Subject>(), new HashSet<Grade>());
        studentList.add(student);
        listResponseEntity = new ResponseEntity<>(studentList, HttpStatus.OK);
        studentDTO = new StudentDTO("sdfg", 1, new HashSet<Integer>());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTopStudentsBySubject() {
        Mockito.when(studentService.getTopStudents(1)).thenReturn(studentList);
        ResponseEntity<List<Student>> responseEntity = studentController.getTopStudentsBySubject(1);
        Assertions.assertEquals(listResponseEntity, responseEntity);
    }

    @Test
    void getTopStudents() {
        Mockito.when(studentService.getTopStudents()).thenReturn(studentList);
        ResponseEntity<List<Student>> responseEntity = studentController.getTopStudents();
        Assertions.assertEquals(listResponseEntity, responseEntity);
    }

    @Test
    void putStudent() {
        Mockito.when(studentService.putStudent(studentDTO)).thenReturn(Optional.ofNullable(student));
        ResponseEntity<Student> responseEntity = studentController.putStudent(studentDTO);
        ResponseEntity<Student> testResponseEntity = new ResponseEntity<>(student, HttpStatus.OK);
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void getFailingStudentsBySubjectId() {
        Mockito.when(studentService.getTopStudents(1)).thenReturn(studentList);
        ResponseEntity<List<Student>> responseEntity = studentController.getTopStudentsBySubject(1);
        Assertions.assertEquals(listResponseEntity, responseEntity);
    }
}