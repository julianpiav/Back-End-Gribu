package co.edu.unisabana.Gribu.Repositories;

import co.edu.unisabana.Gribu.Entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}
