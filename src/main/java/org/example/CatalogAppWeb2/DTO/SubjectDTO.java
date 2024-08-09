package org.example.CatalogAppWeb2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "materii", schema = "mainschema")
@Tag(name = "Subject")
public class SubjectDTO {


    @Schema(name = "name", example = "Math")
    @Column(name = "numematerie")
    private String name;


    @Schema(name = "id", example = "1")
    @NotNull
    @Max(1000000)
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
