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

    private String recoveryToken;

    private int level;
    @Enumerated(EnumType.STRING)
    @Column(name = "avatar", nullable = false)
    private UserAvatar avatar;

    @ElementCollection
    @CollectionTable(name = "user_logged_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "logged_day")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> loggedDays = new HashSet<>();

    @Column(name = "creation_date",nullable = false)
    private ZonedDateTime creationDate;

    @Column(name = "update_date",nullable = false)
    private ZonedDateTime updateDate;

    @Column(name = "user_role",nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<LessonUser> lessonUsers;
    private Float watchedMinutes;
    private int watchedLessons;

}
