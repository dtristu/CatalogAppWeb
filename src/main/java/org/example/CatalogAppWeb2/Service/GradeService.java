package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    GradeUndoService gradeUndoService;
    @Autowired
    StudentService studentService;
    @Transactional
    public Optional<Grade> getGrade(int id) {
        return Optional.ofNullable(gradeRepository.getGradeByGradeId(id));
    }
    @Transactional
    public Optional<Grade> deleteGrade(int id) {
        Optional<Grade> g= Optional.ofNullable(gradeRepository.getGradeByGradeId(id));
        if (g.isPresent()) try {
            gradeUndoService.addToStack(g.get(),null,"delete");
            gradeRepository.deleteGradeByGradeId(id);
        } catch (EmptyResultDataAccessException ignored){}
        return g;
    }
    @Transactional
    public Optional<Grade> postGrade(Grade g) {
        if (studentService.doesAssociationExist(g.getStudentId(),g.getSubjectId()))
        {g.setGradeId(0);
        g=gradeRepository.save(g);
        gradeUndoService.addToStack(g,null,"post");
        return Optional.of(g);}
        else { return Optional.empty();}
    }
    @Transactional
    public Optional<Grade> putGrade(Grade g) {
        if (studentService.doesAssociationExist(g.getStudentId(),g.getSubjectId())){
        Optional<Grade> g2= Optional.ofNullable(gradeRepository.getGradeByGradeId(g.getGradeId()));
        if (g2.isPresent())
        {   gradeRepository.save(g);
            gradeUndoService.addToStack(g,g2.get(),"put");}
        else
        {   g2=Optional.of(g);
            gradeRepository.save(g);
            gradeUndoService.addToStack(g,g2.get(),"putNew");}
        return Optional.of(g);}
        else {return Optional.empty();}
    }
}
