package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LabelRepository;
import co.edu.unisabana.Gribu.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LessonLabelService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private LabelRepository labelRepository;
    public void addLabelToLesson(Long lessonId, Long labelId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Leccion no encontrada"));
        Label label = labelRepository.findById(labelId).orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada"));
        lesson.getLabels().add(label);
        lessonRepository.save(lesson);
    }

    public void removeLabelFromLesson(Long lessonId, Long labelId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Leccion no encontrada"));
        Label label = labelRepository.findById(labelId).orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada"));
        lesson.getLabels().remove(label);
        lessonRepository.save(lesson);
    }

    public Set<Lesson> getLessonsByLabel(Long labelId) {
        Label label = labelRepository.findById(labelId).orElseThrow(() -> new ResourceNotFoundException("Etiqueta no encontrada"));
        return label.getLessons();
    }
}
