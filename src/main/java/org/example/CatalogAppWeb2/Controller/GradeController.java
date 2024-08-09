package org.example.CatalogAppWeb2.Controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Service.GradeService;
import org.example.CatalogAppWeb2.Service.GradeUndoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/grades",
        produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@Tag(name = "Grade API")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    GradeUndoService gradeUndoService;

    @Operation(summary = "Get a Grade by id", description = "Returns a Grade with the corresponding id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grade.class))),
            @ApiResponse(responseCode = "404", description = "Grade not found"),})
    @GetMapping(value = "/{id}")
    public ResponseEntity<Grade> getGrade(@Parameter(name = "id", description = "Grade id",
            example = "1") @PathVariable("id") int id) {
        Optional<Grade> o = gradeService.getGrade(id);
        if (o.isPresent()) {
            return new ResponseEntity<>(o.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a Grade by id", description = "Deletes a Grade with the corresponding id", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grade.class))),
            @ApiResponse(responseCode = "404", description = "Grade not found"),})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Grade> deleteGrade(@Parameter(name = "id", description = "Product id",
            example = "1") @PathVariable("id") int id) {
        Optional<Grade> o = gradeService.deleteGrade(id);
        if (o.isPresent()) {
            return new ResponseEntity<>(o.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Posts a Grade", description = "Posts a Grade with the corresponding details", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grade.class))),
            @ApiResponse(responseCode = "404", description = "Grade not found"),})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Grade> postGrade(@Parameter(description = "A Grade object", required = true,
            content = @Content(
                    schema = @Schema(implementation = Grade.class))) @Valid @org.springframework.web.bind.annotation.RequestBody Grade g) {
        Optional<Grade> o = gradeService.postGrade(g);
        if (o.isPresent()) {
            return new ResponseEntity<Grade>(g, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Puts a Grade", description = "Puts a Grade with the corresponding details", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Grade.class))),
            @ApiResponse(responseCode = "404", description = "Grade not found"),})
    @PutMapping(consumes = "application/json")
    public ResponseEntity<Grade> putGrade(@RequestBody(description = "A Grade object", required = true,
            content = @Content(
                    schema = @Schema(implementation = Grade.class))) Grade g) {
        Optional<Grade> o = gradeService.putGrade(g);
        if (o.isPresent()) {
            return new ResponseEntity<Grade>(g, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //nu sunt sigur
    @Operation(summary = "Undo", description = "Reverts the last operation", responses = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string")))})
    @GetMapping(value = "/undo")
    public ResponseEntity<String> undo() {
        return new ResponseEntity<String>(gradeUndoService.undo(), HttpStatus.OK);
    }
}

