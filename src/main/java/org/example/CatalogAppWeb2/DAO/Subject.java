package org.example.CatalogAppWeb2.DAO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="materii",schema = "mainschema")
public class Subject {

    @Column(name = "numematerie")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmaterie")
    public int id;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "studenti_materii", schema = "mainschema",
            joinColumns = {@JoinColumn(name = "codmaterie")},
            inverseJoinColumns = {@JoinColumn(name = "nrmatricol")}
    )
    @JsonBackReference
    private List<Student> students;

    public Subject() {
    }

    public Subject(String name, int id, List<Student> students) {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
