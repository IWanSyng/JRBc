package work.iwansyng.iwansyng.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Instructor extends User {
    public Instructor() {
    }
}
