package co.edu.unisabana.Gribu.controller.user;

import co.edu.unisabana.Gribu.service.LessonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user/watchLesson")
public class LessonUserController {
    @Autowired
    private LessonUserService lessonUserService;


    @PostMapping(path = "/{userID}/{lessonId}")
    public ResponseEntity<Long> addLessonToUser(@PathVariable Long userId, @PathVariable Long lessonId) {
        lessonUserService.addLessonToUser(lessonId,userId);
        return new ResponseEntity<>(lessonUserService.addLessonToUser(lessonId,userId), HttpStatus.OK);
    }
    @PostMapping(path = "/score/{lessonUserId}/{score}")
    public ResponseEntity<String> saveUser(@PathVariable Long LessonUserId,@PathVariable int score) {
        lessonUserService.addScoreToLessonUser(LessonUserId,score);
        return new ResponseEntity<>("Calificacion guardada correctamente", HttpStatus.OK);
    }


}
