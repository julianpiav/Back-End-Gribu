package co.edu.unisabana.Gribu.controller.user;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/labels")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Label>> getLabels() {
        return new ResponseEntity<>(labelService.getLabels(), HttpStatus.OK);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Label> getLabelByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(labelService.getLabelByName(name), HttpStatus.OK);
    }



}
