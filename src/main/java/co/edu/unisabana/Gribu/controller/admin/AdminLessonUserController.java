package co.edu.unisabana.Gribu.controller.admin;

import co.edu.unisabana.Gribu.dto.LessonDTOMapper;
import co.edu.unisabana.Gribu.entity.LessonUser;
import co.edu.unisabana.Gribu.service.LessonService;
import co.edu.unisabana.Gribu.service.LessonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin/lessonUser")
public class AdminLessonUserController {
    @Autowired
    private LessonService lessonService;
    @Autowired
    private LessonUserService lessonUserService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<LessonUser>> getLessonUsers() {
        return new ResponseEntity<>(lessonUserService.getLessonsUsers(), HttpStatus.OK);
    }

}
