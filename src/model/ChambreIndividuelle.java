package model;

public class ChambreIndividuelle extends Chambre{

    private int id;
    private boolean etat;

    public ChambreIndividuelle(int id, String codeChambre, Etage etage, int id1, boolean etat) {
        super(id, codeChambre, etage);
        this.id = id1;
        this.etat = etat;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "ChambreIndividuelle{" +
                "id=" + id +
                ", etat=" + etat +
                '}';
    }
}
