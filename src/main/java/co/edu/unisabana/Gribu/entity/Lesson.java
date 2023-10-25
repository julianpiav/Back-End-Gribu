package co.edu.unisabana.Gribu.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;
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

    @URL
    private String url_downloadable;

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
    private List<Label> labels;
    @ManyToMany
    @JoinTable(
            name = "lesson_route",
            joinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id",referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<Route> routes;

}
