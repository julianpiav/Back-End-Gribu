package co.edu.unisabana.Gribu.repository;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    List<Lesson> findByLabelsIn(List<Label> labels);
    List<Lesson> findByRoutesIn(List<Route> routes);

}
