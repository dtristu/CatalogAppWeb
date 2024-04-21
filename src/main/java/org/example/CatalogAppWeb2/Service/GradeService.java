package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Transactional
    public Optional<Grade> getGrade(int id) {
        Optional<Grade> g= Optional.ofNullable(gradeRepository.getGradeByGradeId(id));
        return g;
    }
    @Transactional
    public Optional<Grade> deleteGrade(int id) {
        Optional<Grade> g= Optional.ofNullable(gradeRepository.getGradeByGradeId(id));
        if (g.isPresent()) try {
            gradeUndoService.addToStack(g.get(),null,"delete");
            gradeRepository.deleteGradeByGradeId(id);
        } catch (EmptyResultDataAccessException e){}
        return g;
    }
    @Transactional
    public Grade postGrade(Grade g) {
        g.setGradeId(0);
        g=gradeRepository.save(g);
        gradeUndoService.addToStack(g,null,"post");
        return g;
    }
    @Transactional
    public Grade putGrade(Grade g) {
        Optional<Grade> g2= Optional.ofNullable(gradeRepository.getGradeByGradeId(g.getGradeId()));
        if (g2.isPresent())
        {   gradeRepository.save(g);
            gradeUndoService.addToStack(g,g2.get(),"put");}
        else
        {   g2=Optional.of(g);
            gradeRepository.save(g);
            gradeUndoService.addToStack(g,g2.get(),"putNew");}
        return g;
    }
}
