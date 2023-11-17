package co.edu.unisabana.Gribu.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @Column(nullable = false,unique = true)
    private Long id;

    @URL
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String name;

    private Boolean favorite;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Downloadable> downloadableResources;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<LessonUser> lessonUsers;

    @ManyToMany
    @JoinTable(
            name = "lesson_label",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "label_id",referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Label> labels;
    @ManyToMany
    @JoinTable(
            name = "lesson_route",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id",referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Route> routes;

    @Column(name = "average_Score")
    private Double averageScore;


    public void updateAverageScore() {
        if (lessonUsers == null || lessonUsers.isEmpty()) {
            this.averageScore = null;
        } else {
            int totalScore = lessonUsers.stream().mapToInt(LessonUser::getScore).sum();
            this.averageScore = (double) (totalScore / lessonUsers.size());
        }
    }

}
