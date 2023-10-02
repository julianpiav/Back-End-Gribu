package co.edu.unisabana.Gribu.Services;

import co.edu.unisabana.Gribu.Entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class UserDTO {

    private String email;
    private String name;
    private String username;
    private int level;
    private int dayStreak;
    private String alliance;
    private UserRole userRole;

}
