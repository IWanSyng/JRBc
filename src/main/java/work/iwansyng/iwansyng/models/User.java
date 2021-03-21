package work.iwansyng.iwansyng.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
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
    private String passWord;

//    @Column(nullable = false, unique = true)
//    @NotBlank
//    @Email(message = "{errors.invalid_email}")
//    private String email;

    @Column
    private Boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date created;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() { return id; }

    // TODO: investigate whether these methods are mandatory!!!
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

    public Boolean getActive() { return isActive; }

    public void setActive(Boolean active) { isActive = active; }

    public Date getCreated() { return created; }

    public void setCreated(Date created) { this.created = created; }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Role> role) { this.roles = roles; }
    public void addRole(Role role) {
        this.roles.add(role);
    }
}
