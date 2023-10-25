package co.edu.unisabana.Gribu.repository;

import co.edu.unisabana.Gribu.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
    Route findByNameIgnoreCase(String name);
}
