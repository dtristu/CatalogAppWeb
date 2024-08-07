package org.example.CatalogAppWeb2.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

@Entity
@Table(name ="materii",schema = "mainschema")
@Tag(name= "Subject")
public class SubjectDTO {

    @Column(name = "numematerie")
    @Schema(name = "Subject name", example = "Math")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmaterie")
    @Schema(name = "Subject Id", example = "1")
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
