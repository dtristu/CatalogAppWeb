package org.example.CatalogAppWeb2.Service;

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
import java.util.stream.Collectors;

@Transactional
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectService subjectService;

    private record StudentAverage(double average, Student student) {
    }

    public boolean doesAssociationExist (int studentId, int subjectId){
    boolean b =false;
        Student student=studentRepository.getStudentById(studentId);
        Set<Subject> subjects=student.getSubjects();
        subjects=subjects.stream().filter(e->e.getId()==subjectId).collect(Collectors.toSet());
        if (!subjects.isEmpty())
        {b=true;}
    return b;}

    //Overloaded
    public List<Student> getTopStudents() {
        List<Student> students = (List<Student>) studentRepository.findAll();

        List<StudentAverage> orderedStudents=new ArrayList<>();
        for (Student student : students) {
            double avg = student.getGrades().stream().mapToInt(Grade::getGradeValue).average().orElse(0);
            orderedStudents.add(new StudentAverage(avg, student));
        }
        //orderedStudents.sort(e-> e.average );
        //bubble sort
        int n=orderedStudents.size();
        for(int i = 0;i < n; i++){
            for(int j=0;j < n - 1; j++){
                //change sign to change order < or >
                if(orderedStudents.get(j).average < orderedStudents.get(j).average)
                {
                    StudentAverage temp= orderedStudents.get(j);
                    orderedStudents.set(j,orderedStudents.get(j+1));
                    orderedStudents.set(j+1,temp);
                }
            }
        }
        List<Student> result=new ArrayList<>();
        for(int i = 0;i < n; i++)
        {
            result.add(orderedStudents.get(i).student());
        }
        return result;
    }
    //Overloaded
    //just removes averages and leaves the ordered students list
    public List<Student> getTopStudents(int subjectId) {
        List<StudentAverage> orderedStudents=getTopStudentsAndAverages(subjectId);
        List<Student> result=new ArrayList<>();

        for (StudentAverage orderedStudent : orderedStudents) {
            result.add(orderedStudent.student());
        }
        return result;
    }
    //actual method body
    private List<StudentAverage> getTopStudentsAndAverages(int subjectId){
        List<Student> students = subjectService.getStudentsBySubject(subjectId);

        for (Student student : students) {
            Set<Grade> grades = student.getGrades();
            grades = grades.stream().filter(e -> e.getSubjectId() == subjectId).collect(Collectors.toSet());
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
        return new ArrayList<>(topStudentsSet);}

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
        }*/
        List<StudentAverage> orderedStudents=new ArrayList<>();
        for (Student student : students) {
            double avg = student.getGrades().stream().mapToInt(Grade::getGradeValue).average().orElse(0);
            orderedStudents.add(new StudentAverage(avg, student));
        }
        //bubble sort
        int n=orderedStudents.size();
        for(int i = 0;i < n; i++){
            for(int j=0;j < n - 1; j++){
                //change sign to change order < or >
                if(orderedStudents.get(j).average < orderedStudents.get(j).average)
                {
                    StudentAverage temp= orderedStudents.get(j);
                    orderedStudents.set(j,orderedStudents.get(j+1));
                    orderedStudents.set(j+1,temp);
                }
            }
        }
        return orderedStudents;
    }
    @Transactional
    public Optional<Student> putStudent(StudentDTO studentDTO) {
        Student s=new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());

        Set<Integer> subjectsId=studentDTO.getSubjectsId();
        for(Integer i:subjectsId)
        {
         Subject subject=subjectRepository.getSubjectById(i);
         s.addSubject(subject);
         Set<Student> students=subject.getStudents();
         students.add(s);
         subject.setStudents(students);
        }

        s=studentRepository.save(s);

        return Optional.of(s);
    }

    public List<Student> getFailingStudents(int subjectId){
        List<StudentAverage> studentAvg=getTopStudentsAndAverages(subjectId);
        studentAvg.removeIf(s -> s.average >= 5);
        return studentAvg.stream().map(s->s.student).toList();
    }

}
