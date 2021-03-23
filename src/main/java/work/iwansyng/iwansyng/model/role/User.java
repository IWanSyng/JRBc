package work.iwansyng.iwansyng.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String username;

    // TODO: We will need to check user input and catch before input to db
    @Column(nullable = false)
    @NotBlank
    @Size(min = 8)
    private String password;

    @Column
    private Boolean isEnabled;

    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date(System.currentTimeMillis());

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}
