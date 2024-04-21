package org.example.CatalogAppWeb2.Repository;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade,Integer> {
    Grade getGradeByGradeId(int gradeId);

    void deleteGradeByGradeId(int gradeId);
}
