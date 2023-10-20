package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.Entities.Lesson;
import co.edu.unisabana.Gribu.Repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLesson(Long id) {
        return lessonRepository.findById(id);
    }

    public void saveOrUpdate(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
