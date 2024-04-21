package org.example.CatalogAppWeb2.Controller;

import org.example.CatalogAppWeb2.DAO.Grade;
import org.example.CatalogAppWeb2.Service.GradeService;
import org.example.CatalogAppWeb2.Service.GradeUndoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/grades",
        produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class GradeController {
    @Autowired
    GradeService gradeService;
    @Autowired
    GradeUndoService gradeUndoService;
@GetMapping(value = "/{id}")
    public ResponseEntity<Grade> getGrade(@PathVariable("id") int id){
    Optional<Grade> o=gradeService.getGrade(id);
    if (o.isPresent()){
        return new ResponseEntity<>(o.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    }

@DeleteMapping(value = "/{id}")
public ResponseEntity<Grade> deleteGrade(@PathVariable("id") int id) {
    Optional<Grade> o=gradeService.deleteGrade(id);
    if (o.isPresent()){
        return new ResponseEntity<>(o.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    }
@PostMapping(consumes = "application/json")
    public ResponseEntity<Grade> postGrade(@RequestBody Grade g)
{
    Optional<Grade> o=gradeService.postGrade(g);
    if (o.isPresent())
    {return new ResponseEntity<Grade>(g,HttpStatus.OK);}
    else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}

}
@PutMapping(consumes = "application/json")
    public ResponseEntity<Grade> putGrade(@RequestBody Grade g){
    Optional<Grade> o=gradeService.putGrade(g);
    if (o.isPresent())
    {return new ResponseEntity<Grade>(g,HttpStatus.OK);}
    else {return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);}
}
@GetMapping(value = "/undo")
    public String undo(){
    return gradeUndoService.undo();
}
}

