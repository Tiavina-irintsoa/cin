package devise;

public class Cours {
    Devise devise;
    double taux;
    int idcours;
    public Devise getDevise() {
        return devise;
    }
    public void setDevise(Devise devise) {
        this.devise = devise;
    }
    public double getTaux() {
        return taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }
    public int getIdcours() {
        return idcours;
    }
    public void setIdcours(int idcours) {
        this.idcours = idcours;
    }
}
