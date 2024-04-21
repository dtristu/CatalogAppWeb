package org.example.CatalogAppWeb2.Service;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Stack;


@Service
@Transactional
public class GradeUndoService {
    private final Stack<Command> stack=new Stack<>();
    @Autowired
    GradeRepository gradeRepository;
    private static class Command {
        private final String lastCommand;
        private final Grade grade;
        private final Grade grade2;
        public Command(Grade g,Grade g2,String s) {
            this.grade=g;
            this.lastCommand=s;
            this.grade2=g2;
        }

    }

    public void addToStack(Grade g,Grade g2,String s)
    {
        stack.push(new Command(g, g2, s));
        if (stack.size()>10){
            stack.removeElementAt(0);
        }
    }
    public String undo()
    {
        String r="";
        if (stack.isEmpty())
        {return "Nothing to undo!"; }
        Command c= stack.peek();
        stack.pop();
        switch (c.lastCommand){
            case "get":
                r="Get command. Nothing to undo.";
                        break;
            case "delete":
                undoDelete(c.grade);
                r="Undid detete!";
                break;
            case "put":
                undoPut(c.grade,c.grade2);
                r="Undid put!";
                break;
            case "putNew":
                undoPutNew(c.grade,c.grade2);
                r="Undid put!";
                break;
            case "post":
                undoPost(c.grade);
                r="Undid post!";
                break;
        }
    return r;}
    @Transactional
    private void undoPost(Grade grade) {
        try {
            gradeRepository.deleteGradeByGradeId(grade.getGradeId());
        } catch (EmptyResultDataAccessException ignored){}

    }
    @Transactional
    private void undoPutNew(Grade grade, Grade grade2) {
        if (grade.getGradeId() != grade2.getGradeId()) {
            gradeRepository.deleteGradeByGradeId(grade.getGradeId());}
        else {
           try {
               gradeRepository.deleteGradeByGradeId(grade.getGradeId());
           } catch (EmptyResultDataAccessException ignored){}
            gradeRepository.save(grade2);}
    }
    @Transactional
    private void undoPut(Grade grade, Grade grade2) {
        try{
            gradeRepository.deleteGradeByGradeId(grade.getGradeId());
            gradeRepository.save(grade2);
        } catch (EmptyResultDataAccessException ignored){}
    }
    @Transactional
    private void undoDelete(Grade g)
    {
        g.setGradeId(0);
        gradeRepository.save(g);
    }
}
