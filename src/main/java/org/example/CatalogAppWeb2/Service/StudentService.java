package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public boolean doesAssociationExist (int studentId, int subjectId){
    boolean b =false;
        Student student=studentRepository.getStudentById(studentId);
        List<Subject> subjects=student.getSubjects();
        subjects=subjects.stream().filter(e->e.getId()==subjectId).toList();
        if (!subjects.isEmpty())
        {b=true;}
    return b;}

}
