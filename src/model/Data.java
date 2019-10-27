package model;

public class Data {
    private String nom;
    private String roomId;

    public Data(String nom, String roomId) {
        this.nom = nom;
        this.roomId = roomId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}

