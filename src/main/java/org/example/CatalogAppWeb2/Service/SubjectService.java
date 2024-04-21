package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.example.CatalogAppWeb2.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GradeRepository gradeRepository;

    public List<Subject> getSubjects() {
        Iterable<Subject> s=subjectRepository.findAll();
        List<Subject> subjects=new ArrayList<>();
    s.forEach(subjects::add);
    return subjects;
    }

    public List<Student> getStudentsBySubject(int id) {
    Subject subject=subjectRepository.getSubjectById(id);
    for (Student student:subject.getStudents())
    {List<Grade> grades= student.getGrades();
        grades=grades.stream().filter(e->e.getSubjectId()==id).toList();
        student.setGrades(grades);
    }
    return subject.getStudents();}

    public List<Grade> getGradesBySubjectAndStudent(int subjectId, int studentId) {
    return gradeRepository.getGradeBySubjectIdAndStudentId(subjectId,studentId);
    }
}
