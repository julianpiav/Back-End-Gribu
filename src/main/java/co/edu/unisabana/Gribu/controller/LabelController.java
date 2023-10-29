package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/labels")
public class LabelController {
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

    @GetMapping(path = "/name/{name}")
    public ResponseEntity <Label> getLabelByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(labelService.getLabelByName(name), HttpStatus.OK);
    }


    @PostMapping(path = "/save")
    public ResponseEntity<String> saveLabel(@RequestBody Label label) {
        labelService.SaveOrUpdateLabel(label);
        return new ResponseEntity<>("Leccion guardada con Exito", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLabelById(@PathVariable("id") Long id) {
        labelService.deleteLabelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteLabelByName(@PathVariable("name") String name) {
        labelService.deleteLabelByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
