package co.edu.unisabana.Gribu.Repositories;

import co.edu.unisabana.Gribu.Entities.LessonUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonUserRepository extends JpaRepository<LessonUser, Long> {
}
