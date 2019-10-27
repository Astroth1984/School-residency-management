package model;

public class Resident {

    private Student std;
    private int idroom;

    public Resident(Student std, int idroom) {
        this.std = std;
        this.idroom = idroom;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public int getIdroom() {
        return idroom;
    }

    public void setIdroom(int idroom) {
        this.idroom = idroom;
    }
}
