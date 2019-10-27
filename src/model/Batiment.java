package model;

public class Batiment {

    private int id;
    private String code;
    private String gender;
    private int NBEtages;

    public Batiment()
    {

    }

    public Batiment(int id, String gender, String code, int NBEtages) {
        this.id = id;
        this.gender = gender;
        this.NBEtages = NBEtages;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNBEtages() {
        return NBEtages;
    }

    public void setNBEtages(int NBEtages) {
        this.NBEtages = NBEtages;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", NBEtages=" + NBEtages +
                '}';
    }
}
