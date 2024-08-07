package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.Repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentService studentService;
    @Mock
    SubjectService subjectService;

    private static DataForTests dataForTests;

    @BeforeAll
    static void setUp() {
        dataForTests=new DataForTests();
    }

    @Test
    void doesAssociationExist() {
        Optional<Student> student1=dataForTests.getStudentSet().stream().filter(e-> e.getId()==1).findFirst();
        Mockito.when(studentRepository.getStudentById(1)).thenReturn(student1.get());

        boolean result = studentService.doesAssociationExist(1,1);
        assertTrue(result);
    }

    @Test
    void getTopStudents() {
        Mockito.when(studentRepository.findAll()).thenReturn(List.copyOf(dataForTests.getStudentSet()));
        List<Student> result = studentService.getTopStudents();

        Assertions.assertEquals(1,result.get(0).getId());
    }

    @Test
    void GetTopStudents2() {
        Mockito.when(subjectService.getStudentsBySubject(1))
                .thenReturn(dataForTests.getStudentSet()
                .stream().filter(e->e.getSubjects().stream().anyMatch(ee->ee.getId()==1)).toList());
        List<Student> result = studentService.getTopStudents(1);
        Assertions.assertEquals(1,result.get(0).getId());
    }

    @Test
    void putStudent() {

    }

    @Test
    void getFailingStudents() {
    }
}