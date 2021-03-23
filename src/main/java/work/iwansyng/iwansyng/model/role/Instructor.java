package work.iwansyng.iwansyng.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import work.iwansyng.iwansyng.model.Course;

import javax.persistence.*;

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
}
