package co.edu.unisabana.Gribu.service;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.exception.ExistingResourceException;
import co.edu.unisabana.Gribu.exception.ResourceNotFoundException;
import co.edu.unisabana.Gribu.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    RouteRepository routeRepository;
    public List<Route> getRoutes() {
        if (routeRepository.findAll().isEmpty()){
            throw new ResourceNotFoundException("No se encontraron rutas");
        }else {
            return routeRepository.findAll();
        }
    }

    public Route getRouteByName(String name) {
        if (routeRepository.findByNameIgnoreCase(name)==null){
            throw new ResourceNotFoundException("La ruta con el nombre= "+name+" no existe");
        }else {
            return routeRepository.findByNameIgnoreCase(name);
        }

    }
    public Optional<Route> getRouteByID(Long id) {
        if (routeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("La ruta con el id= "+id+" no existe");
        }else {
            return routeRepository.findById(id);
        }

    }
    public void saveRoute(Route route) {
        if (routeRepository.findAll().isEmpty()) {
            routeRepository.save(route);
        }else if (routeRepository.findByNameIgnoreCase(route.getName())!=null){
            throw new ExistingResourceException("La ruta que intenta crear ya existe");
        }else {
            routeRepository.save(route);
        }
    }
    public void updateRoute(Route route) {
        if (routeRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No hay rutas para moodificar");
        }else if (routeRepository.findByNameIgnoreCase(route.getName())==null){
            throw new ResourceNotFoundException("La ruta que intenta modificar no existe");
        }else {
            routeRepository.save(route);
        }
    }

    public void deleteRouteByName(String name) {
        if (routeRepository.findByNameIgnoreCase(name)==null){
            throw new ResourceNotFoundException("La ruta con el nombre= "+name+", no existe.");
        }else {
            routeRepository.deleteById(routeRepository.findByNameIgnoreCase(name).getId());
        }
    }
    public void deleteRouteById(Long id) {
        if (routeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("La ruta con el id= "+id+", no existe.");
        }else {
            routeRepository.deleteById(id);
        }
    }
    public List<Lesson> getLessonsByRoute(Route route){
        if (routeRepository.findByNameIgnoreCase(route.getName())==null){
            throw new ResourceNotFoundException("La ruta con el nombre= "+route.getName()+", no existe.");
        }else if (route.getLessons().isEmpty()){
            throw new ResourceNotFoundException("No hay lecciones con la ruta= "+route.getName());
        }else {
            return route.getLessons();
        }
    }
}
