package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.Entities.Route;
import co.edu.unisabana.Gribu.Repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService {
    @Autowired
    RouteRepository routeRepository;

    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRoute(Long id) { return routeRepository.findById(id); }

    public void saveOrUpdate(Route route) {
        routeRepository.save(route);
    }
    public void delete(Long id) { routeRepository.deleteById(id); }
}
