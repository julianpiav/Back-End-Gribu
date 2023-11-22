package co.edu.unisabana.Gribu.controller.user;

import co.edu.unisabana.Gribu.dto.LessonDTO;
import co.edu.unisabana.Gribu.dto.LessonDTOMapper;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.service.LessonLabelService;
import co.edu.unisabana.Gribu.service.LessonRouteService;
import co.edu.unisabana.Gribu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/user/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private LessonLabelService lessonLabelService;
    @Autowired
    private LessonRouteService lessonRouteService;
    private LessonDTOMapper lessonDTOMapper;

    @GetMapping("/getLessonsByLabel/{labelId}")
    public ResponseEntity<Set<Lesson>> getLessonsByLabel(@PathVariable Long labelId) {
        return new ResponseEntity<>(lessonLabelService.getLessonsByLabel(labelId), HttpStatus.OK);
    }

    @GetMapping("/getLessonsByRoute/{routeId}")
    public ResponseEntity<Set<Lesson>> getLessonsByRoute(@PathVariable Long routeId) {
        return new ResponseEntity<>(lessonRouteService.getLessonsByRoute(routeId), HttpStatus.OK);
    }
    @GetMapping(path = "/all")
    public ResponseEntity<List<Lesson>> getLessons() {
        return new ResponseEntity<>(lessonService.getLessons(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<LessonDTO>> getLessonById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(lessonService.getLessonById(id)
                .map(lessonDTOMapper), HttpStatus.OK);

    }

    @GetMapping(path = "/getRandomFavorite")
    public ResponseEntity<Optional<LessonDTO>> getRandomFavoriteLesson() {
        return new ResponseEntity<>(lessonService.getRandomFavoriteLesson()
                .map(lessonDTOMapper), HttpStatus.OK);

    }
}
