package co.edu.unisabana.Gribu.Entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int level;

    @Column(name = "day_streak")
    private int dayStreak;

    @Column(name = "creation_date",nullable = false)
    private LocalDate creationDate;

    @Column(name = "upadate_date",nullable = false)
    private LocalDate updateDate;

    private String alliance;

    @Column(name = "user_role",nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private Set<LessonUser> lessonUsers;

}
