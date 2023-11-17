package co.edu.unisabana.Gribu.controller.admin;

import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/admin/routes")
public class AdminRouteController {
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

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveRoute(@RequestBody Route route) {
        routeService.saveRoute(route);
        return new ResponseEntity<>("Ruta guardada con Exito", HttpStatus.CREATED);
    }
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        routeService.updateRoute(route);
        return new ResponseEntity<>("Ruta modificada con Exito", HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable("id") Long id) {
        routeService.deleteRouteById(id);
        return new ResponseEntity<>("La ruta con el id= "+id+" ha sido borrada",HttpStatus.OK);
    }
}
