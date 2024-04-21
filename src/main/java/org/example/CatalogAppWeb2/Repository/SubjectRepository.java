package org.example.CatalogAppWeb2.Repository;

import org.example.CatalogAppWeb2.DAO.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject,Integer> {
    public Subject getSubjectById(int id);
}
