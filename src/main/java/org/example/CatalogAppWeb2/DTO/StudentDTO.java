package org.example.CatalogAppWeb2.DTO;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name ="studenti",schema = "mainschema")
public class StudentDTO {
    @Column(name = "nume")
    private String name;
    @Id
    @Column(name = "nrmatricol")
    private int id;

    private Set<Integer> subjectsId;

    public StudentDTO() {
    }

    public StudentDTO(String name, int id, Set<Integer> subjectsId) {
        this.name = name;
        this.id = id;
        this.subjectsId = subjectsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(Set<Integer> subjectsId) {
        this.subjectsId = subjectsId;
    }
}

