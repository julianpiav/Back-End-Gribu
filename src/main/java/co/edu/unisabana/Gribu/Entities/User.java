package co.edu.unisabana.Gribu.Entities;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private int level;

    @Column(name = "day_streak")
    private int dayStreak;

    @Column(name = "creation_date",nullable = false)
    private ZonedDateTime creationDate;

    @Column(name = "upadate_date",nullable = false)
    private ZonedDateTime updateDate;

    private String alliance;

    @Column(name = "user_role",nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<LessonUser> lessonUsers;
}
