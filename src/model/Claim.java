package model;

import java.util.Date;

public class Claim {

    private int id;
    private String category;
    private String text;
    private Date date;
    private boolean valide;
    private Student std;

    public Claim(int id,String category, String text, Date date, boolean valide, Student std) {
        this.category = category;
        this.text = text;
        this.date = date;
        this.valide = valide;
        this.std = std;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }
}
