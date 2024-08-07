package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.SubjectDTO;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.example.CatalogAppWeb2.Repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @Mock
    SubjectRepository subjectRepository;
    @Mock
    GradeRepository gradeRepository;
    @InjectMocks
    SubjectService subjectService;

    static DataForTests dataForTests;

    @BeforeAll
    static void setUp() {
       dataForTests=new DataForTests();
    }

    @Test
    void getSubjects() {
        Iterable<Subject> iterable = dataForTests.getSubjectSet();

        Mockito.when(subjectRepository.findAll()).thenReturn(iterable);
        List<Subject> subjectList = subjectService.getSubjects();

        Assertions.assertEquals(2,subjectList.size());
    }

    @Test
    void getStudentsBySubject() {
        Subject subject=dataForTests.getSubjectSet().stream().filter(e->e.getId()==1).toList().get(0);

        Mockito.when(subjectRepository.getSubjectById(1)).thenReturn(subject);
        List<Student> result = subjectService.getStudentsBySubject(1);

        Assertions.assertEquals(2,result.size());
    }

    @Test
    void getGradesBySubjectAndStudent() {
        List<Grade> expected = new ArrayList<>();
        expected= dataForTests.getGradeSet().stream().filter(e-> e.getGradeId()==1&&e.getSubjectId()==1).toList();

        Mockito.when(gradeRepository.getGradeBySubjectIdAndStudentId(1,1)).thenReturn(expected);
        Assertions.assertEquals(1,gradeRepository.getGradeBySubjectIdAndStudentId(1,1).size());
    }

    @Test
    void putSubject() {
        SubjectDTO subjectDTO = new SubjectDTO("adb", 1);
        Subject subject = new Subject("adb",1,null);

        Mockito.when(subjectRepository.save(Mockito.any())).thenReturn(subject);
        Optional<SubjectDTO> result = subjectService.putSubject(subjectDTO);

        Assertions.assertEquals(1,result.get().getId());
        Assertions.assertEquals(Optional.of(subjectDTO),result);
    }
}