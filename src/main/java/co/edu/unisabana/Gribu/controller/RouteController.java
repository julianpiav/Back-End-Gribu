package co.edu.unisabana.Gribu.controller;

import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Route>> getRoutes() {
        return new ResponseEntity<>(routeService.getRoutes(), HttpStatus.OK);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Optional<Route>> getRouteById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(routeService.getRouteByID(id), HttpStatus.OK);
    }
    @GetMapping(path = "/name/{name}")
    public ResponseEntity <Route> getRouteByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(routeService.getRouteByName(name), HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveRoute(@RequestBody Route route) {
        routeService.saveRoute(route);
        return new ResponseEntity<>("Leccion guardada con Exito", HttpStatus.CREATED);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        routeService.updateRoute(route);
        return new ResponseEntity<>("Leccion modificada con Exito", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRouteById(@PathVariable("id") Long id) {
        routeService.deleteRouteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteRouteByName(@PathVariable("name") String name) {
        routeService.deleteRouteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
