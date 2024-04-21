package org.example.CatalogAppWeb2.Repository;

import org.example.CatalogAppWeb2.DAO.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    Student getStudentById (int id);
}
