package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Service.GradeService;
import org.example.CatalogAppWeb2.Service.GradeUndoService;
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
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class GradeControllerTest {

    @Mock
    GradeService gradeService;
    @Mock
    GradeUndoService gradeUndoService;
    @InjectMocks
    GradeController gradeController;
    Grade testGrade;
    ResponseEntity<Grade> testResponseEntity;

    @BeforeEach
    void setUp() {

        testGrade = new Grade(2, 3, new Date(1234), 4, 5);
        testResponseEntity = new ResponseEntity<>(testGrade, HttpStatus.OK);
    }

    @Test
    void getGrade() {
        Mockito.when(gradeService.getGrade(2)).thenReturn(Optional.ofNullable(testGrade));
        ResponseEntity<Grade> responseEntity = gradeController.getGrade(2);
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void deleteGrade() {
        Mockito.when(gradeService.deleteGrade(2)).thenReturn(Optional.ofNullable(testGrade));
        ResponseEntity<Grade> responseEntity = gradeController.deleteGrade(2);
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void postGrade() {
        Mockito.when(gradeService.postGrade(testGrade)).thenReturn(Optional.ofNullable(testGrade));
        ResponseEntity<Grade> responseEntity = gradeController.postGrade(testGrade);
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void putGrade() {
        Mockito.when(gradeService.putGrade(testGrade)).thenReturn(Optional.ofNullable(testGrade));
        ResponseEntity<Grade> responseEntity = gradeController.putGrade(testGrade);
        Assertions.assertEquals(testResponseEntity, responseEntity);
    }

    @Test
    void undo() {
        Mockito.when(gradeUndoService.undo()).thenReturn("test");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(gradeUndoService.undo(), HttpStatus.OK);
        Assertions.assertEquals(new ResponseEntity<String>("test", HttpStatus.OK), responseEntity);

    }
}