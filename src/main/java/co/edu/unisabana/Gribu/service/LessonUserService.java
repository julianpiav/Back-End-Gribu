package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.LessonUser;
import co.edu.unisabana.Gribu.entity.User;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LessonRepository;
import co.edu.unisabana.Gribu.repository.LessonUserRepository;
import co.edu.unisabana.Gribu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class LessonUserService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonUserRepository lessonUserRepository;

    public List<LessonUser> getLessonsUsers() {
        if (lessonUserRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se encontro que usuarios hayan visto lecciones");
        }else {
            return lessonUserRepository.findAll();
        }
    }
    public Long addLessonToUser(Long lessonId, Long userId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Leccion con no encontrada"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        LessonUser lessonUser = new LessonUser();
        lessonUser.setLesson(lesson);
        user.setWatchedLessons(user.getWatchedLessons()+1);
        lessonUser.setUser(user);
        lessonUser.setDate(ZonedDateTime.now());
        lessonUserRepository.save(lessonUser);
        return lessonUser.getId();
    }

    public void addScoreToLessonUser(Long lessonUserId, int score) {
        LessonUser lessonUser = lessonUserRepository.findById(lessonUserId).orElseThrow(() -> new ResourceNotFoundException("LeccionUsuario no encontrada"));
        if (score>5||score<0){
            throw new IllegalArgumentException("Puntaje invalido");
        }
        lessonUser.setScore(score);
        lessonUserRepository.save(lessonUser);
    }

    public void lessonSeenByUser(Long lessonUserId) {
        LessonUser lessonUser = lessonUserRepository.findById(lessonUserId).orElseThrow(() -> new ResourceNotFoundException("LeccionUsuario no encontrada"));
        lessonUser.setSeen(Boolean.TRUE);
        lessonUserRepository.save(lessonUser);
    }
}

