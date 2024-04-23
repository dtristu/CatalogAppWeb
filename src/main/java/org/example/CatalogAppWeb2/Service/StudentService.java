package org.example.CatalogAppWeb2.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.DAO.Student;
import org.example.CatalogAppWeb2.DAO.Subject;
import org.example.CatalogAppWeb2.DTO.StudentDTO;
import org.example.CatalogAppWeb2.Repository.StudentRepository;
import org.example.CatalogAppWeb2.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Transactional
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectService subjectService;
    @PersistenceContext
    private EntityManager entityManager;
    public boolean doesAssociationExist (int studentId, int subjectId){
    boolean b =false;
        Student student=studentRepository.getStudentById(studentId);
        List<Subject> subjects=student.getSubjects();
        subjects=subjects.stream().filter(e->e.getId()==subjectId).toList();
        if (!subjects.isEmpty())
        {b=true;}
    return b;}
    //Overloaded
    public List<Student> getTopStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        Object[][] orderedStudents=new Object[2][students.size()];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double avg= student.getGrades().stream().mapToInt(Grade::getGradeValue).average().orElse(0);
            orderedStudents[0][i]=student;
            orderedStudents[1][i]=avg;
        }
        //bubble sort
        int n=orderedStudents[0].length;
        for(int i = 0;i < n; i++){
            for(int j=0;j < n - 1; j++){
                //change sign to change order < or >
                if((double)orderedStudents[1][j] < (double)orderedStudents[1][j+1])
                {
                    Object temp1 = orderedStudents[1][j];
                    Object temp0 =orderedStudents[0][j];

                    orderedStudents[1][j] = orderedStudents[1][j+1];
                    orderedStudents[0][j] = orderedStudents[0][j+1];

                    orderedStudents[1][j+1] = temp1;
                    orderedStudents[0][j+1] = temp0;
                }
            }
        }
        List<Student> result=new ArrayList<>();
        for(int i = 0;i < n; i++)
        {
            result.add((Student) orderedStudents[0][i]);
        }
        return result;
    }
    //Overloaded
    public List<Student> getTopStudents(int subjectId) {
        List<Student> students = subjectService.getStudentsBySubject(subjectId);

        for (Student student : students) {
            List<Grade> grades = student.getGrades();
            grades = grades.stream().filter(e -> e.getSubjectId() == subjectId).toList();
            student.setGrades(grades);
        }

        /*Map<Student,Double> map =new HashMap<>();
        for (Student s:students)
        {  Double avg= s.getGrades().stream().mapToInt(Grade::getGradeValue).average().orElse(0);
            map.put(s,avg);}
        Map<Student, Double> orderedStudents = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(orderedStudents.values());
        Set<Student> topStudentsSet= orderedStudents.keySet();
        return new ArrayList<>(topStudentsSet);}*/
        Object[][] orderedStudents=new Object[2][students.size()];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            double avg= student.getGrades().stream().mapToInt(Grade::getGradeValue).average().orElse(0);
            orderedStudents[0][i]=student;
            orderedStudents[1][i]=avg;
            }
        //bubble sort
        int n=orderedStudents[0].length;
        for(int i = 0;i < n; i++){
            for(int j=0;j < n - 1; j++){
                //change sign to change order < or >
                if((double)orderedStudents[1][j] < (double)orderedStudents[1][j+1])
                {
                    Object temp1 = orderedStudents[1][j];
                    Object temp0 =orderedStudents[0][j];

                    orderedStudents[1][j] = orderedStudents[1][j+1];
                    orderedStudents[0][j] = orderedStudents[0][j+1];

                    orderedStudents[1][j+1] = temp1;
                    orderedStudents[0][j+1] = temp0;
                }
            }
        }
        List<Student> result=new ArrayList<>();
        for(int i = 0;i < n; i++)
        {
           result.add((Student) orderedStudents[0][i]);
        }
        return result;
    }
    @Transactional
    public Optional<Student> putStudent(StudentDTO studentDTO) {
        Student s=new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());
        List<Integer> subjectsId=studentDTO.getSubjectsId();
        for(Integer i:subjectsId)
        {
         Subject subject=subjectRepository.getSubjectById(i);
         s.addSubject(subject);
         List<Student> students=subject.getStudents();
         students.add(s);
         subject.setStudents(students);
        }

        s=entityManager.merge(s);

        return Optional.of(s);
    }
}
