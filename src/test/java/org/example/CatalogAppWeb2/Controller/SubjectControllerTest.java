package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.SubjectDTO;
import org.example.CatalogAppWeb2.Service.SubjectService;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SubjectControllerTest {

    @Mock
    SubjectService subjectService;
    @InjectMocks
    SubjectController subjectController;

    List<Subject> subjectList;
    ResponseEntity<List<Subject>> testResponseEntity;
    ArrayList<Student> studentList;
    Student student;
    ResponseEntity<ArrayList<Student>> listResponseEntityStudent;
    Grade testGrade;
    List<Grade> gradeList;
    ResponseEntity<List<Grade>> listResponseEntityGrade;

    @BeforeEach
    void setUp() {
        Subject subject1 = new Subject("abc", 1, new HashSet<Student>());
        subjectList = new ArrayList<>();
        subjectList.add(subject1);
        testResponseEntity = new ResponseEntity<>(subjectList, HttpStatus.OK);

        studentList = new ArrayList<>();
        student = new Student("abc", 1, new HashSet<Subject>(), new HashSet<Grade>());
        studentList.add(student);
        listResponseEntityStudent = new ResponseEntity<>(studentList, HttpStatus.OK);

        testGrade = new Grade(2, 3, new Date(1234), 4, 5);
        gradeList = new ArrayList<>();
        gradeList.add(testGrade);
        listResponseEntityGrade = new ResponseEntity<>(gradeList, HttpStatus.OK);
    }

    @Test
    void getSubjects() {
        Mockito.when(subjectService.getSubjects()).thenReturn(subjectList);
        ResponseEntity<List<Subject>> responseEntity = subjectController.getSubjects();
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void getStudentsBySubject() {
        Mockito.when(subjectService.getStudentsBySubject(1)).thenReturn(studentList);
        ResponseEntity<List<Student>> responseEntity = subjectController.getStudentsBySubject(1);
        Assertions.assertEquals(listResponseEntityStudent, responseEntity);
    }

    @Test
    void getGradesBySubjectAndStudent() {
        Mockito.when(subjectService.getGradesBySubjectAndStudent(1, 1)).thenReturn(gradeList);
        ResponseEntity<List<Grade>> responseEntity = subjectController.getGradesBySubjectAndStudent(1, 1);
        Assertions.assertEquals(listResponseEntityGrade, responseEntity);
    }

    @Test
    void putSubject() {
        SubjectDTO subjectDTO = new SubjectDTO("abc", 1);
        ResponseEntity<SubjectDTO> testResponseEntitySubject = new ResponseEntity<>(subjectDTO, HttpStatus.OK);

        Mockito.when(subjectService.putSubject(subjectDTO)).thenReturn(Optional.of(subjectDTO));
        ResponseEntity<SubjectDTO> responseEntity = subjectController.putSubject(subjectDTO);
        Assertions.assertEquals(testResponseEntitySubject, responseEntity);
    }
}