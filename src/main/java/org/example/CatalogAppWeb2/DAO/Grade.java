package org.example.CatalogAppWeb2.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name ="note",schema = "mainschema")
//@Access(AccessType.PROPERTY)
@Tag(name="Grade")
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
    @Schema(name = "Grade Id", example = "1")
    private int gradeId;

    @Column (name ="valoareanotei")
    @Schema(name = "Grade Value", example = "10")
    private int gradeValue;
    @Column (name = "data")
    @Basic
    @Schema(name = "Data")
    private Date date;
    @Schema(name = "Subject Id", example = "1")
    @Column (name ="materiecod")
    private int subjectId;
    @Column(name ="student")
    @Schema(name = "Student Id", example = "1")
    private int studentId;
    @ManyToOne
    @JoinColumn (name ="student", insertable = false, updatable = false )
    @JsonIgnore
    //nu am reusit sa scot eroarea cu lazy load
    @Schema(name = "Student")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return getGradeId() == grade.getGradeId() && getGradeValue() == grade.getGradeValue() && getSubjectId() == grade.getSubjectId() && getStudentId() == grade.getStudentId() && Objects.equals(getDate(), grade.getDate()) && Objects.equals(getStudent(), grade.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGradeId());
    }
}
