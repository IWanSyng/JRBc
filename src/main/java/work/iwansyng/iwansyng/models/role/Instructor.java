package work.iwansyng.iwansyng.models.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.models.Course;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Boolean isActive = true;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "instructor_courses",
            joinColumns = {@JoinColumn(name = "instructor_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses;

    public void assignCourse(Course course) {
        if (courses == null) {
            courses = new HashSet<>();
        }
        courses.add(course);
    }
}
