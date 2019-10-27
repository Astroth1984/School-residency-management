package model;

public class Etage {

    private int id;
    private String codeEtage;
    private int NBChambres;
    private Batiment batiment;

    public Etage(int id, String codeEtage, int NBChambres, Batiment batiment) {
        this.id = id;
        this.codeEtage = codeEtage;
        this.NBChambres = NBChambres;
        this.batiment = batiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeEtage() {
        return codeEtage;
    }

    public void setCodeEtage(String codeEtage) {
        this.codeEtage = codeEtage;
    }

    public int getNBChambres() {
        return NBChambres;
    }

    public void setNBChambres(int NBChambres) {
        this.NBChambres = NBChambres;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", codeEtage='" + codeEtage + '\'' +
                ", NBChambres=" + NBChambres +
                '}';
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }
}
