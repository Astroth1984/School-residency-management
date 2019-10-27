package model;

public class Student extends User{
    private int id;
    private String level;
    private String CNE;

    public Student(int id, String name, String email, String phone, String cin, int id1, String level, String CNE) {
        super(id, name, email, phone, cin);
        this.id = id1;
        this.level = level;
        this.CNE = CNE;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCNE() {
        return CNE;
    }

    public void setCNE(String CNE) {
        this.CNE = CNE;
    }

    @Override
    public String toString() {
        return "{" +
                "idStudent=" + id +
                ", level='" + level + '\'' +
                ", CNE='" + CNE + '\'' +
                '}';
    }

    public int getIdUser() {
        return super.getId();
    }
}
