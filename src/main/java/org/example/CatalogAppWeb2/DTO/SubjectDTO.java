package org.example.CatalogAppWeb2.DTO;

import jakarta.persistence.*;

@Entity
@Table(name ="materii",schema = "mainschema")
public class SubjectDTO {

    @Column(name = "numematerie")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmaterie")
    public int id;

    public SubjectDTO() {
    }

    public SubjectDTO(String name, int id) {
        this.name = name;
        this.id = id;
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
}
