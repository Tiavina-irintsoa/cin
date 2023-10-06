package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import sante.Citoyen;
import sante.FicheSante;

@Path("/citoyen")
@Stateless
public class CitoyenService {

  @PersistenceContext(name = "santePersistenceUnit")
  private EntityManager manager;

  @GET
  @Path("/getSante/{cin}")
  @Produces(MediaType.APPLICATION_JSON)
  public FicheSante getCitoyenSante(@PathParam("cin") String cin) {
    String req = "select * from citoyen where idcin = '" + cin + "'";
    Query query = manager.createNativeQuery(req, FicheSante.class);
    List<FicheSante> fiche = (List<FicheSante>) query.getResultList();

    return fiche.get(0);
  }

  @GET
  @Path("/get/{cin}")
  @Produces(MediaType.APPLICATION_JSON)
  public Citoyen getCitoyen(@PathParam("cin") String cin) {
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
