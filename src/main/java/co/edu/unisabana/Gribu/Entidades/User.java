package co.edu.unisabana.Gribu.Entidades;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String username;
    private String password;
    private int level;
    private int dayStreak;
    private LocalDate creationDate;
    private LocalDate updateDate;
    private String alliance;
    private UserRole userRole;
}
