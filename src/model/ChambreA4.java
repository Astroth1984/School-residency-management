package model;

public class ChambreA4 extends Chambre{

    private int id;
    private boolean etatPlace1;
    private boolean etatPlace2;
    private boolean etatPlace3;
    private boolean etatPlace4;

    public ChambreA4(int id, String codeChambre, Etage etage, int id1, boolean etatPlace1, boolean etatPlace2, boolean etatPlace3, boolean etatPlace4) {
        super(id, codeChambre, etage);
        this.id = id1;
        this.etatPlace1 = etatPlace1;
        this.etatPlace2 = etatPlace2;
        this.etatPlace3 = etatPlace3;
        this.etatPlace4 = etatPlace4;
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

    public boolean isEtatPlace3() {
        return etatPlace3;
    }

    public void setEtatPlace3(boolean etatPlace3) {
        this.etatPlace3 = etatPlace3;
    }

    public boolean isEtatPlace4() {
        return etatPlace4;
    }

    public void setEtatPlace4(boolean etatPlace4) {
        this.etatPlace4 = etatPlace4;
    }

    @Override
    public String toString() {
        return "ChambreA4{" +
                "id=" + id +
                ", etatPlace1=" + etatPlace1 +
                ", etatPlace2=" + etatPlace2 +
                ", etatPlace3=" + etatPlace3 +
                ", etatPlace4=" + etatPlace4 +
                '}';
    }
}
