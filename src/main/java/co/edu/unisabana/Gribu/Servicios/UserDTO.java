package co.edu.unisabana.Gribu.Servicios;

import co.edu.unisabana.Gribu.Entidades.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private String email;
    private String name;
    private String username;
    private int level;
    private int dayStreak;
    private String alliance;
    private UserRole userRole;

    public UserDTO(String email, String name, String username, int level, int dayStreak, String alliance, UserRole userRole) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.level = level;
        this.dayStreak = dayStreak;
        this.alliance = alliance;
        this.userRole = userRole;
    }
}
