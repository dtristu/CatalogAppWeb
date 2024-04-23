package org.example.CatalogAppWeb2.DTO;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="studenti",schema = "mainschema")
public class StudentDTO {
    @Column(name = "nume")
    private String name;
    @Id
    @Column(name = "nrmatricol")
    private int id;

    private List<Integer> subjectsId;

    public StudentDTO() {
    }

    public StudentDTO(String name, int id, List<Integer> subjectsId) {
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

    public List<Integer> getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(List<Integer> subjectsId) {
        this.subjectsId = subjectsId;
    }
}

