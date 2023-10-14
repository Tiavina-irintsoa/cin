package ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sante.FicheFoncier;

@Stateless
public class EjbFoncier implements FoncierRemote {

  @PersistenceContext(name = "santePersistenceUnit")
  private EntityManager manager;

  @Override
  public FicheFoncier getFicheFoncier(String idcin) throws Exception {
    FicheFoncier fiche = new FicheFoncier(idcin); 
    fiche.getData(manager);
    return fiche;
  }
}
