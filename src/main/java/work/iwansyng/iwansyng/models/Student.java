package work.iwansyng.iwansyng.models;


public class Student extends User {

    private Integer uniqueId;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }
}
