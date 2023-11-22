package co.edu.unisabana.Gribu.entity;

import javax.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson_user")
public class LessonUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    private ZonedDateTime date;

    private Float watchedMinutes;

    @Column(name = "score")
    private int score;

}
