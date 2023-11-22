package co.edu.unisabana.Gribu.controller.admin;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/admin/labels")
public class AdminLabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Label>> getLabels() {
        return new ResponseEntity<>(labelService.getLabels(), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Optional<Label>> getLabelById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(labelService.getLabelById(id), HttpStatus.OK);
    }


    @PostMapping(path = "/save")
    public ResponseEntity<String> saveLabel(@RequestBody Label label) {
        labelService.saveLabel(label);
        return new ResponseEntity<>("Etiqueta guardada con Exito", HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<String> updateLabel(@RequestBody Label label) {
        labelService.updateLabel(label);
        return new ResponseEntity<>("Etiqueta modificada con Exito", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLabelById(@PathVariable("id") Long id) {
        labelService.deleteLabelById(id);
        return new ResponseEntity<>("La etiqueta con el id= "+id+" ha sido borrada",HttpStatus.OK);
    }
}
