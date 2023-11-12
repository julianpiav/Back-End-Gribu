package co.edu.unisabana.Gribu.dto;


import java.time.DayOfWeek;
import java.util.Set;

public record UserDTO (
        String email,
        String username,
        String name,
        Integer level,
        Set<DayOfWeek> DayStreak,
        String alliance
){

}
