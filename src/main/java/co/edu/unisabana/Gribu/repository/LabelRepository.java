package co.edu.unisabana.Gribu.repository;

import co.edu.unisabana.Gribu.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
    Label findByNameIgnoreCase(String labelName);

    Set<Label> findByNameInIgnoreCase(Set<String> labelNames);

}
