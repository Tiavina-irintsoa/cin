package sante;

public class Citoyen {
    String idcin; 
    String nom; 
    String prenom; 
    Allergie[] allergies; 
    Operation[] operations; 
    Maladie[] maladies;
    public String getIdcin() {
        return idcin;
    }
    public void setIdcin(String idcin) {
        this.idcin = idcin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Allergie[] getAllergies() {
        return allergies;
    }
    public void setAllergies(Allergie[] allergies) {
        this.allergies = allergies;
    }
    public Operation[] getOperations() {
        return operations;
    }
    public void setOperations(Operation[] operations) {
        this.operations = operations;
    }
    public Maladie[] getMaladies() {
        return maladies;
    }
    public void setMaladies(Maladie[] maladies) {
        this.maladies = maladies;
    } 
}
