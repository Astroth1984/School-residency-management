package model;

public class Chambre {

    private int id;
    private String codeChambre;
    private Etage etage;

    public Chambre(int id, String codeChambre, Etage etage) {
        this.id = id;
        this.codeChambre = codeChambre;
        this.etage = etage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeChambre() {
        return codeChambre;
    }

    public void setCodeChambre(String codeChambre) {
        this.codeChambre = codeChambre;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", codeChambre='" + codeChambre + '\'' +
                ", etage=" + etage +
                '}';
    }
}
