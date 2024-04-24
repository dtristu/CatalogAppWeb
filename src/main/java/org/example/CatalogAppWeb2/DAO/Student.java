package org.example.CatalogAppWeb2.DAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="studenti",schema = "mainschema")
public class Student {
    @Column(name = "nume")
    private String name;
    @Id
    @Column(name = "nrmatricol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "students")
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<Grade> grades;

    public Student(String name, int id, Set<Subject> subjects, Set<Grade> grades) {
        this.name = name;
        this.id = id;
        this.subjects = subjects;
        this.grades = grades;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
    public void addSubject(Subject subject)
    {
        Set<Subject> currentSubjects = Objects.requireNonNullElseGet(this.subjects, HashSet::new);
        currentSubjects.add(subject);
        this.subjects=currentSubjects;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}

