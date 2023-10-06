package sante;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "allergie")
public class Allergie implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  int idAllergie;

  String allergie;

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public int getIdAllergie() {
    return idAllergie;
  }

  public void setIdAllergie(int idAllergie) {
    this.idAllergie = idAllergie;
  }

  public String getAllergie() {
    return allergie;
  }

  public void setAllergie(String allergie) {
    this.allergie = allergie;
  }

  @Override
  public String toString() {
    return (
      "Allergie [idAllergie=" + idAllergie + ", allergie=" + allergie + "]"
    );
  }
}
