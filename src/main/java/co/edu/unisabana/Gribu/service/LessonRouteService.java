package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.LabelRepository;
import co.edu.unisabana.Gribu.repository.LessonRepository;
import co.edu.unisabana.Gribu.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class LessonRouteService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private RouteRepository routeRepository;
    public Lesson addRouteToLesson(Long lessonId, Long routeId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Leccion no encontrada"));
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada"));
        lesson.getRoutes().add(route);
        return lessonRepository.save(lesson);
    }

    public Lesson removeRouteFromLesson(Long lessonId, Long routeID) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Leccion no encontrada"));
        Route route = routeRepository.findById(routeID).orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada"));
        lesson.getRoutes().remove(route);
        return lessonRepository.save(lesson);
    }

    public Set<Lesson> getLessonsByRoute(Long routeId) {
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada"));
        return route.getLessons();
    }
}
