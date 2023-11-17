package co.edu.unisabana.Gribu.entity;

import javax.persistence.*;

@Entity
@Table(name = "downloadable_resources")
public class Downloadable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String Name;
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}
