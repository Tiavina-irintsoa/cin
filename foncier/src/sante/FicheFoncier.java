package sante;

import foncier.Bien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class FicheFoncier {

  Citoyen citoyen;
  List<Bien> biens;

  public List<Bien> getListeBien(EntityManager manager) {
    String req = "select * from bien where idcitoyen = '" + citoyen.getIdcin() + "'";
    Query query = manager.createNativeQuery(req, Bien.class);
    List<Bien> biens = (List<Bien>) query.getResultList();
    return biens;
  }

  public FicheFoncier(String idcin){
    this.citoyen = new Citoyen(idcin);
  }
  public void getData(EntityManager manager) throws Exception{
    this.citoyen = Citoyen.getCitoyenInEjb(this.citoyen.getIdcin());
    this.setBiens(getListeBien(manager));
  }

  public List<Bien> getBiens() {
    return biens;
  }

  public void setBiens(List<Bien> biens) {
    this.biens = biens;
  }

public Citoyen getCitoyen() {
    return citoyen;
}

public void setCitoyen(Citoyen citoyen) {
    this.citoyen = citoyen;
}
}
