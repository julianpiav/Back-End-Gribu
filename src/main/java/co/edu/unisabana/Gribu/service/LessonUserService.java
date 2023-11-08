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

@Service
public class LessonUserService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonUserRepository lessonUserRepository;

    public LessonUser addLessonToUser(Long lessonId, Long userId, ZonedDateTime date, int score) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        LessonUser lessonUser = new LessonUser();
        lessonUser.setLesson(lesson);
        lessonUser.setUser(user);
        lessonUser.setDate(date);
        lessonUser.setScore(score);
        return lessonUserRepository.save(lessonUser);
    }
}

