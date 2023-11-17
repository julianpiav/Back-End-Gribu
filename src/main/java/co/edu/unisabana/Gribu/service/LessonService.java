package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Downloadable;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getLessons() {
        if (lessonRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se encontraron lecciones");
        }else {
            return lessonRepository.findAll();
        }
    }
    public Optional<Lesson> getLessonById(Long id) {
        return Optional.ofNullable(lessonRepository.findById(id).orElseThrow((() -> new ResourceNotFoundException(
                "Usuario con el ID " + id + ", no encontrado."))));
    }
    public Optional<Lesson> getRandomFavoriteLesson() {
        List<Lesson> favoriteLessons = lessonRepository.findByFavoriteIsTrue();
        if (favoriteLessons.isEmpty()) {
            throw new ResourceNotFoundException("No hay lecciones favoritas");
        }else {
            int randomIndex = new Random().nextInt(favoriteLessons.size());
            return Optional.ofNullable(favoriteLessons.get(randomIndex));
        }
    }
    public void saveLesson(Lesson lesson) {
        if (lessonRepository.findAll().isEmpty()) {
            lessonRepository.save(lesson);
        }else if (lessonRepository.findById(lesson.getId()).isPresent()){
            throw new ExistingResourceException("La Leccion que intenta guardar ya existe en la base de datos");
        }else {
            lessonRepository.save(lesson);
        }
    }
    public void updateLesson(Lesson lesson) {
        if (lessonRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No hay lecciones para modificar");
        } else if (lessonRepository.findById(lesson.getId()).isEmpty()){
            throw new ResourceNotFoundException("La leccion con el ID \"+id+\", no existe.");

        }else {
            lessonRepository.save(lesson);
        }
    }

    public void deleteLesson(Long id) {
        if (lessonRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No hay lecciones para modificar");
        } else if (lessonRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("La leccion con el ID "+id+", no existe.");
        }else {
            lessonRepository.deleteById(id);
        }
    }

    public void addDownloadable(Long lessonId, Downloadable downloadable) {
        if (lessonRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No hay lecciones para modificar");
        }else if (lessonRepository.findById(lessonId).isEmpty()){
            throw new ResourceNotFoundException("La Leccion que intenta modificar no existe en la base de datos");
        }else {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow((()-> new ResourceNotFoundException("La Leccion que intenta modificar no existe en la base de datos")));
            lesson.getDownloadableResources().add(downloadable);
            lessonRepository.save(lesson);
        }
    }
}
