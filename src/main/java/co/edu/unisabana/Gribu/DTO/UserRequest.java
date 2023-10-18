package co.edu.unisabana.Gribu.DTO;

import co.edu.unisabana.Gribu.Entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String email;
    private String name;
    private String username;
    private int level;
    private int dayStreak;
    private String alliance;
    private UserRole userRole;

}
