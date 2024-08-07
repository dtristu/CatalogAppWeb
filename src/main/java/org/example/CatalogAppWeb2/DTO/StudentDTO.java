package org.example.CatalogAppWeb2.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name ="studenti",schema = "mainschema")
@Tag(name= "Student DTO")
public class StudentDTO {
    @Column(name = "nume")
    @Schema(name = "Student name", example = "Kevin")
    private String name;
    @Id
    @Column(name = "nrmatricol")
    @Schema(name = "Student ID", example = "1")
    private int id;
    @Schema(name = "Subjects, only Id")
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

