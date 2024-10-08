package org.example.CatalogAppWeb2.DAO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "studenti", schema = "mainschema")
@Tag(name = "Student")
public class Student {
    @Schema(name = "name", example = "Kevin")
    @Column(name = "nume")
    @NotNull(message = "Student name cannot be null")
    private String name;

    @Schema(name = "id", example = "1")
    @Id
    @Column(name = "nrmatricol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(name = "subjects")
    @ManyToMany(mappedBy = "students")
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private Set<Subject> subjects;

    @Schema(name = "grades")
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

    public void addSubject(Subject subject) {
        Set<Subject> currentSubjects = Objects.requireNonNullElseGet(this.subjects, HashSet::new);
        currentSubjects.add(subject);
        this.subjects = currentSubjects;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId() && Objects.equals(getName(), student.getName()) && Objects.equals(getSubjects(), student.getSubjects()) && Objects.equals(getGrades(), student.getGrades());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}

