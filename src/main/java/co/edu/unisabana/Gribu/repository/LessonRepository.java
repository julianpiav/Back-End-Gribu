package co.edu.unisabana.Gribu.repository;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Lesson;
import co.edu.unisabana.Gribu.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Set<Lesson> findByLabelsIn(List<Label> labels);
    Set<Lesson> findByRoutesIn(List<Route> routes);

    Set<Lesson> findLessonsByLabels_NameInIgnoreCase(Collection<String> labelNames);
}
