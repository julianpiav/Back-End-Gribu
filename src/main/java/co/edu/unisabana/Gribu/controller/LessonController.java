package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

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
        lessonService.SaveOrUpdateLesson(lesson);
        return new ResponseEntity<>("Leccion guardada con Exito", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable("id") Long id) {
        lessonService.deleteLesson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
