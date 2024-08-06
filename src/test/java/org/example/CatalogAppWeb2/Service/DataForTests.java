package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class DataForTests {
    private final Set<Student> studentSet;
    private final Set<Subject> subjectSet;
    private final Set<Grade> gradeSet;

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public Set<Grade> getGradeSet() {
        return gradeSet;
    }

    public DataForTests() {
        subjectSet=new HashSet<>();
        studentSet=new HashSet<>();
        gradeSet=new HashSet<>();

        Subject subject1= new Subject("subject1",1,null);
        Subject subject2= new Subject("subject2",2,null);
        subjectSet.add(subject1);
        subjectSet.add(subject2);

        Student student1=new Student("name1",1,subjectSet,null);
        Student student2=new Student("name2",2, Set.of(subject1),null);
        studentSet.add(student1);
        studentSet.add(student2);

        subject1.setStudents(studentSet);
        subject2.setStudents(Set.of(student1));

        Grade grade1=new Grade(1,9,new Date(123),1,1);
        Grade grade2=new Grade(2,10,new Date(456),1,2);
        Grade grade3=new Grade(3,9,new Date(789),2,1);
        gradeSet.add(grade1);
        gradeSet.add(grade2);
        gradeSet.add(grade3);

        student1.setGrades(Set.of(grade1,grade2));
        student2.setGrades(Set.of(grade3));
    }
}
