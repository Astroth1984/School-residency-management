package model;

public class Admin extends User{

    private int id;
    private int matricule;

    public Admin(int id, String name, String email, String phone, String cin, int id1, int matricule) {
        super(id, name, email, phone, cin);
        this.id = id1;
        this.matricule = matricule;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", matricule=" + matricule +
                '}';
    }
}
