package org.example.CatalogAppWeb2.Repository;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade,Integer> {
    Grade getGradeByGradeId(int gradeId);

    void deleteGradeByGradeId(int gradeId);

    List<Grade> getGradeBySubjectIdAndStudentId(int subjectId,int studentId);
}
