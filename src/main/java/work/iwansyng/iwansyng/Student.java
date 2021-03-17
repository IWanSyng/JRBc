package work.iwansyng.iwansyng;

public class Student extends User{

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    private int uniqueId;
}
