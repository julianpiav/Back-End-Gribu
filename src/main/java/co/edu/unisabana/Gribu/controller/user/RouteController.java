package co.edu.unisabana.Gribu.controller.user;

import co.edu.unisabana.Gribu.entity.Route;
import co.edu.unisabana.Gribu.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Route>> getRoutes() {
        return new ResponseEntity<>(routeService.getRoutes(), HttpStatus.OK);
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity <Route> getRouteByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(routeService.getRouteByName(name), HttpStatus.OK);
    }

}
