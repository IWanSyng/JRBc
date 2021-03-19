package work.iwansyng.iwansyng.models;

import javax.persistence.*;

public class Student extends User {

    private Integer uniqueId;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }
}
