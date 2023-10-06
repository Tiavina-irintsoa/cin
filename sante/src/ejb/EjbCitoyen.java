package ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.PathParam;
import java.util.List;
import sante.Citoyen;
import sante.FicheSante;

@Stateless
public class EjbCitoyen implements EjbRemote {

  @PersistenceContext(name = "santePersistenceUnit")
  private EntityManager manager;

  @Override
  public Citoyen getCitoyen(String cin) {
     String req = "select * from citoyen where idcin = '" + cin + "'";
    Query query = manager.createNativeQuery(req, FicheSante.class);
    List<FicheSante> citoyens = (List<FicheSante>) query.getResultList();
    for (Citoyen citoyen : citoyens) {
      System.out.println(citoyen);
    }
    if (citoyens.size() == 0) {
      return null;
    }
    FicheSante result = citoyens.get(0);
    Citoyen citoyenResult = new Citoyen(result.getIdcin(),result.getNom(),result.getPrenom(),result.getAdresse(),result.getDateNaissance());
    return citoyenResult;
  }
}
