package work.iwansyng.iwansyng.models;

import javax.persistence.Entity;

@Entity
public class Instructor extends User{

    public Instructor() {
        this.setIsAdmin(true);
    }
}
