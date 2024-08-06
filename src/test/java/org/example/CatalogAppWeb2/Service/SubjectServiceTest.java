package org.example.CatalogAppWeb2.Service;

import org.checkerframework.checker.units.qual.A;
import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.SubjectDTO;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.example.CatalogAppWeb2.Repository.SubjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @Mock
    SubjectRepository subjectRepository;
    @Mock
    GradeRepository gradeRepository;
    @InjectMocks
    SubjectService subjectService;

    Subject subject1;
    Subject subject2;
    Student student1;
    Student student2;

    @BeforeEach
    void setUp() {
        subject1 = new Subject("abc",1,null);
        student1= new Student("def",2,new HashSet<>(),new HashSet<>());
        student2= new Student("ged",3,new HashSet<>(),new HashSet<>());
        Set<Student> studentSet= new HashSet<>();
        studentSet.add(student1);
        studentSet.add(student2);
        subject1.setStudents(studentSet);

        subject2 = new Subject("def",3,null);
    }

    @Test
    void getSubjects() {
        List<Subject> arrayList = new ArrayList<>();
        arrayList.add(subject1);
        arrayList.add(subject2);
        Iterable<Subject> iterable = arrayList;

        List<Subject> subjectListExpected=new ArrayList<>();
        subjectListExpected.add(subject1);
        subjectListExpected.add(subject2);

        Mockito.when(subjectRepository.findAll()).thenReturn(iterable);
        List<Subject> subjectList = subjectService.getSubjects();

        Assertions.assertEquals(subjectListExpected,subjectList);
    }

    @Test
    void getStudentsBySubject() {
        List<Student> expected= new ArrayList<>();
        expected.add(student1);
        expected.add(student2);

        Mockito.when(subjectRepository.getSubjectById(1)).thenReturn(subject1);
        List<Student> result = subjectService.getStudentsBySubject(1);

        Assertions.assertEquals(expected,result);
    }

    @Test
    void getGradesBySubjectAndStudent() {
        List<Grade> expected = new ArrayList<>();
        Grade grade1 =new Grade(1,9,new Date(123),1, 2);
        expected.add(grade1);

        Mockito.when(gradeRepository.getGradeBySubjectIdAndStudentId(1,1)).thenReturn(expected);
        Assertions.assertEquals(expected,gradeRepository.getGradeBySubjectIdAndStudentId(1,1));
    }

    @Test
    void putSubject() {

    SubjectDTO subjectDTO = new SubjectDTO(subject2.getName(), subject2.getId());
    Mockito.when(subjectRepository.save(subject2)).thenReturn(subject2);
    Optional<SubjectDTO> result = subjectService.putSubject(subjectDTO);

    Assertions.assertEquals(Optional.of(subjectDTO),result);

    }
}