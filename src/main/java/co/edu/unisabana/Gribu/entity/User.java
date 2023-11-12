package co.edu.unisabana.Gribu.entity;

import lombok.*;


import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.HashSet;
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

    @ElementCollection
    @CollectionTable(name = "user_logged_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "logged_day")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> loggedDays = new HashSet<>();

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
