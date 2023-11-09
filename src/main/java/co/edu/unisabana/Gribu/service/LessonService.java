package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getLesson() {
        if (lessonRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se encontraron lecciones");
        }else {
            return lessonRepository.findAll();
        }
    }
    public Set<Lesson> getLessonByLabels(List<Label> labels){
        if (lessonRepository.findByLabelsIn(labels)==null){
            throw new ResourceNotFoundException("Leccion no encontrada con las etiquetas= "+labels);
        }else {
            return lessonRepository.findByLabelsIn(labels);
        }
    }
    public Set<Lesson> getLessonByRoute(List<Route> routes){
        if (lessonRepository.findByRoutesIn(routes)==null){
            throw new ResourceNotFoundException("Leccion no encontrada con las rutas= "+routes);
        }else {
            return lessonRepository.findByRoutesIn(routes);
        }
    }

    public Optional<Lesson> getLessonByID(Long id) {
        if (lessonRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Leccion con el ID "+id+", no existe.");
        }else {
            return lessonRepository.findById(id);
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
        }else if (lessonRepository.findById(lesson.getId()).isEmpty()){
            throw new ResourceNotFoundException("La Leccion que intenta modificar no existe en la base de datos");
        }else {
            Lesson updatedLesson = lessonRepository.findById(lesson.getId()).get();

            updatedLesson.setUrl(lesson.getUrl());
            updatedLesson.setName(lesson.getName());
            updatedLesson.setUrlDownloadable(lesson.getUrlDownloadable());
            lessonRepository.save(updatedLesson);
        }
    }

    public void deleteLesson(Long id) {
        if (lessonRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("La leccion con el ID "+id+", no existe.");
        }else {
            lessonRepository.deleteById(id);
        }
    }
}
