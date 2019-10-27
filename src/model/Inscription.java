package model;

import java.util.Date;

public class Inscription {

    private int IdInscription;
    private Date dateInscription;
    private Date dateDebut;
    private int dureeReservation; // en mois
    private boolean current;
    private Chambre chambre;
    private Student student;

    public Inscription(int idInscription, Date dateInscription, Date dateDebut, int dureeReservation,boolean current, Chambre chambre, Student student) {
        IdInscription = idInscription;
        this.dateInscription = dateInscription;
        this.dateDebut = dateDebut;
        this.dureeReservation = dureeReservation;
        this.chambre = chambre;
        this.student = student;
        this.current = current;
    }

    public int getIdInscription() {
        return IdInscription;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setIdInscription(int idInscription) {
        IdInscription = idInscription;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDureeReservation() {
        return dureeReservation;
    }

    public void setDureeReservation(int dureeReservation) {
        this.dureeReservation = dureeReservation;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "IdInscription=" + IdInscription +
                ", dateInscription=" + dateInscription +
                ", dateDebut=" + dateDebut +
                ", dureeReservation=" + dureeReservation +
                ", chambre=" + chambre +
                ", student=" + student +
                '}';
    }
}
