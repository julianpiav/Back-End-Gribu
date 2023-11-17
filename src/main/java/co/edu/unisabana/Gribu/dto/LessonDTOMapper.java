package co.edu.unisabana.Gribu.dto;

import co.edu.unisabana.Gribu.entity.Lesson;

import java.util.function.Function;

public class LessonDTOMapper implements Function<Lesson,LessonDTO> {
    @Override
    public LessonDTO apply(Lesson lesson) {
        return new LessonDTO(
                lesson.getId(),
                lesson.getName(),
                lesson.getDownloadableResources(),
                lesson.getRoutes(),
                lesson.getLabels(),
                lesson.getAverageScore()
        );
        }
}
