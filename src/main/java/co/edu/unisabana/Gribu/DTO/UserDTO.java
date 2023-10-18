package co.edu.unisabana.Gribu.DTO;



public record UserDTO (
        String email,
        String username,
        String name,
        Integer level,
        Integer DayStreak,
        String alliance
){

}
