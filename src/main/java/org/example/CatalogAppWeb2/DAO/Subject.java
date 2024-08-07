package org.example.CatalogAppWeb2.DAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="materii",schema = "mainschema")
@Tag(name= "Subject")
public class Subject {

    @Column(name = "numematerie")
    @Schema(name = "Subject name", example = "Math")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmaterie")
    @Schema(name = "Subject Id", example = "1")
    public int id;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "studenti_materii", schema = "mainschema",
            joinColumns = {@JoinColumn(name = "codmaterie")},
            inverseJoinColumns = {@JoinColumn(name = "nrmatricol")}
    )
    @JsonBackReference
    @Schema(name = "Students")
    private Set<Student> students;

    public Subject() {
    }

    public Subject(String name, int id, Set<Student> students) {
        this.name = name;
        this.id = id;
        this.students = students;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return getId() == subject.getId() && Objects.equals(getName(), subject.getName()) && Objects.equals(getStudents(), subject.getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
