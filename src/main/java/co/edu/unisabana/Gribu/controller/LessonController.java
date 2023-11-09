package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.service.LessonLabelService;
import co.edu.unisabana.Gribu.service.LessonRouteService;
import co.edu.unisabana.Gribu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonLabelService lessonLabelService;
    @Autowired
    private LessonRouteService lessonRouteService;

    @PostMapping("/{lessonId}/addLabel/{labelId}")
    public ResponseEntity<String> addLabelToLesson(@PathVariable Long lessonId, @PathVariable Long labelId) {
        lessonLabelService.addLabelToLesson(lessonId, labelId);
        return new ResponseEntity<>("La etiqueta con el id= "+labelId+" ha sido agregada a la leccion con el id= "+lessonId,HttpStatus.OK);
    }

    @PostMapping("/{lessonId}/deleteLabel/{labelId}")
    public ResponseEntity<String> removeLabelFromLesson(@PathVariable Long lessonId, @PathVariable Long labelId) {
        lessonLabelService.removeLabelFromLesson(lessonId, labelId);
        return new ResponseEntity<>("La etiqueta con el id= "+labelId+" ha sido borrada de la leccion con el id= "+lessonId,HttpStatus.OK);

    }

    @GetMapping("/getLessonsByLabel/{labelId}")
    public ResponseEntity<Set<Lesson>> getLessonsByLabel(@PathVariable Long labelId) {
        return new ResponseEntity<>(lessonLabelService.getLessonsByLabel(labelId),HttpStatus.OK);
    }
    @PostMapping("/{lessonId}/addRoute/{routeId}")
    public ResponseEntity<String> addRouteToLesson(@PathVariable Long lessonId, @PathVariable Long routeId) {
        lessonRouteService.addRouteToLesson(lessonId, routeId);
        return new ResponseEntity<>("La ruta con el id= "+routeId+" ha sido agregada a la leccion con el id= "+lessonId,HttpStatus.OK);

    }

    @PostMapping("/{lessonId}/deleteRoute/{routeId}")
    public ResponseEntity<String> removeRouteFromLesson(@PathVariable Long lessonId, @PathVariable Long routeId) {
        lessonRouteService.removeRouteFromLesson(lessonId, routeId);
        return new ResponseEntity<>("La ruta con el id= "+routeId+" ha sido borrada de la leccion con el id= "+lessonId,HttpStatus.OK);
    }

    @GetMapping("/getLessonsByRoute/{routeId}")
    public ResponseEntity<Set<Lesson>> getLessonsByRoute(@PathVariable Long routeId) {
        return new ResponseEntity<>(lessonRouteService.getLessonsByRoute(routeId),HttpStatus.OK);
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<Lesson>> getLessons() {
        return new ResponseEntity<>(lessonService.getLesson(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Lesson>> getLessonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(lessonService.getLessonByID(id), HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveLesson(@RequestBody Lesson lesson) {
        lessonService.saveLesson(lesson);
        return new ResponseEntity<>("Leccion guardada con Exito", HttpStatus.CREATED);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateLesson(@RequestBody Lesson lesson) {
        lessonService.updateLesson(lesson);
        return new ResponseEntity<>("Leccion modificada con Exito", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable("id") Long id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>("La leccion con el id= "+id+" ha sido borrada",HttpStatus.OK);
    }


}
