package co.edu.unisabana.Gribu.dto;

import co.edu.unisabana.Gribu.entity.Label;
import co.edu.unisabana.Gribu.entity.Route;

import java.util.Set;

public record LessonDTO (
        Long id,
        String name,
        Set<co.edu.unisabana.Gribu.entity.Downloadable> urlDownloadables,
        Set<Route> routes,
        Set<Label> labels,
        Double averageScore
){
}
