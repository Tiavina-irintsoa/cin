package sante;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "citoyen")
public class FicheSante extends Citoyen {

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "citoyen_allergie",
    joinColumns = { @JoinColumn(name = "idcin") },
    inverseJoinColumns = { @JoinColumn(name = "idAllergie") }
  )
  Set<Allergie> allergie;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "idcin")
  Set<Operation> operation;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "citoyen_maladie",
    joinColumns = { @JoinColumn(name = "idcin") },
    inverseJoinColumns = { @JoinColumn(name = "idMaladie") }
  )
  Set<Maladie> maladie;

  public static FicheSante getFicheSante(EntityManager manager, String idcin) {
    String req = "select * from citoyen where idcin = '" + idcin + "'";
    Query query = manager.createNativeQuery(req, FicheSante.class);
    List<FicheSante> citoyens = (List<FicheSante>) query.getResultList();
    FicheSante result = citoyens.get(0);
    return result;
  }

  public FicheSante(String idcin) {
    super(idcin);
  }

  public FicheSante() {
    super();
  }

  public Set<Allergie> getAllergies() {
    return allergie;
  }

  public void setAllergies(Set<Allergie> allergies) {
    this.allergie = allergies;
  }

  public Set<Operation> getOperation() {
    return operation;
  }

  public void setOperation(Set<Operation> operation) {
    this.operation = operation;
  }

  public Set<Maladie> getMaladie() {
    return maladie;
  }

  public void setMaladie(Set<Maladie> maladie) {
    this.maladie = maladie;
  }

  public Set<Allergie> getAllergie() {
    return allergie;
  }

  public void setAllergie(Set<Allergie> allergie) {
    this.allergie = allergie;
  }

  @Override
  public String toString() {
    return (
      super.toString() +
      "FicheSante [allergie=" +
      allergie +
      ", operation=" +
      operation +
      ", maladie=" +
      maladie +
      "]"
    );
  }
}
