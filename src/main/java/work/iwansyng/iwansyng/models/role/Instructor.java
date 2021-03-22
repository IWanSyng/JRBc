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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public void assignCourse(Course course) {
        this.course = course;
    }
}
