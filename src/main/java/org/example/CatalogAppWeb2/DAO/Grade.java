package org.example.CatalogAppWeb2.DAO;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;


@Entity
@Table(name ="note",schema = "mainschema")
//@Access(AccessType.PROPERTY)
public class Grade {
    public Grade() {
    }

    public Grade(int gradeId, int gradeValue, Date date, int studentId, int subjectId) {
        this.gradeId = gradeId;
        this.gradeValue = gradeValue;
        this.date = date;
        this.subjectId = subjectId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "nrnota")
    private int gradeId;

    @Column (name ="valoareanotei")
    private int gradeValue;
    @Column (name = "data")
    @Basic
    private Date date;
    @Column (name ="materiecod")
    private int subjectId;
    @Column(name ="student")
    private int studentId;
    /*@ManyToOne
    @JoinColumn (name ="student", insertable = false, updatable = false )
    @JsonIgnore
    //nu am reusit sa scot eroarea cu lazy load
    //am reusit
    private StudentOfSubject student;
    */

    public int getGradeId() {
        return gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setGradeId(int gradeNr) {
        this.gradeId = gradeNr;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
