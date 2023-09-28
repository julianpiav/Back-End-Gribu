package co.edu.unisabana.Gribu.Repositories;

import co.edu.unisabana.Gribu.Entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
