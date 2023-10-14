package foncier;


import jakarta.persistence.*;

@Entity
public class Bien {
    @Id
    int idbien; 
    String adresse; 
    double superficie;

    public int getIdbien() {
        return idbien;
    }
    public void setIdbien(int idbien) {
        this.idbien = idbien;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public double getSuperficie() {
        return superficie;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

}
