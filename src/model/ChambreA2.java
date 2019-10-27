package model;

public class ChambreA2 extends Chambre {

    private int id;
    private boolean etatPlace1;
    private boolean etatPlace2;

    public ChambreA2(int id, String codeChambre, Etage etage, int id1, boolean etatPlace1, boolean etatPlace2) {
        super(id, codeChambre, etage);
        this.id = id1;
        this.etatPlace1 = etatPlace1;
        this.etatPlace2 = etatPlace2;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isEtatPlace1() {
        return etatPlace1;
    }

    public void setEtatPlace1(boolean etatPlace1) {
        this.etatPlace1 = etatPlace1;
    }

    public boolean isEtatPlace2() {
        return etatPlace2;
    }

    public void setEtatPlace2(boolean etatPlace2) {
        this.etatPlace2 = etatPlace2;
    }

    @Override
    public String toString() {
        return "ChambreA2{" +
                "id=" + id +
                ", etatPlace1=" + etatPlace1 +
                ", etatPlace2=" + etatPlace2 +
                '}';
    }
}
