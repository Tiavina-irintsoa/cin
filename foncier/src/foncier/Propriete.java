package foncier;

import jakarta.persistence.*;

@SqlResultSetMapping(
  name = "ProprieteMapping1",
  classes = @ConstructorResult(
    targetClass = Propriete.class,
    columns = {
      @ColumnResult(name = "geom", type = String.class),
      @ColumnResult(name = "superficie", type = Double.class),
      @ColumnResult(name = "description", type = String.class),
      @ColumnResult(name = "adresse", type = String.class),
      @ColumnResult(name = "perimetre", type = Double.class),
    }
  )
)
@SqlResultSetMapping(
  name = "ProprieteMapping2",
  classes = @ConstructorResult(
    targetClass = Propriete.class,
    columns = {
      @ColumnResult(name = "idpropriete", type = Integer.class),
      @ColumnResult(name = "superficie", type = Double.class),
      @ColumnResult(name = "description", type = String.class),
      @ColumnResult(name = "adresse", type = String.class),
      @ColumnResult(name = "perimetre", type = Double.class),
    }
  )
)
@Entity
@Table(name = "v_propriete")
public class Propriete {

  @Column(name = "idpropriete")
  @Id
  int idpropriete;

  double superficie;
  String description;
  String adresse;
  double perimetre;

  @Transient
  Polygone polygone;

  public Propriete(
    double superficie,
    String description,
    String adresse,
    double perimetre
  ) {
    this.superficie = superficie;
    this.description = description;
    this.adresse = adresse;
    this.perimetre = perimetre;
  }

  public Propriete() {}

  public int getIdpropriete() {
    return idpropriete;
  }

  public Propriete(
    String geom,
    double superficie,
    String description,
    String adresse,
    double perimetre
  ) {
    polygone = new Polygone(geom);
    setSuperficie(superficie);
    setDescription(description);
    setAdresse(adresse);
    this.perimetre = perimetre;
  }

  public Propriete(int idpropriete) {
    setIdpropriete(idpropriete);
  }

  public void setIdpropriete(String idpropriete) {
    setIdpropriete(Integer.valueOf(idpropriete));
  }

  public void getDetails(EntityManager manager) {
    String sql =
      "SELECT idpropriete,geom, superficie, description, adresse ,perimetre FROM v_propriete WHERE idpropriete = :id";
    Propriete result = (Propriete) manager
      .createNativeQuery(sql, "ProprieteMapping1")
      .setParameter("id", idpropriete)
      .getSingleResult();
    polygone = result.getPolygone();
    superficie = result.getSuperficie();
    adresse = result.getAdresse();
    description = result.getDescription();
    this.perimetre = result.getPerimetre();
    System.out.println(result.getPerimetre());
  }

  public void setIdpropriete(int idpropriete) {
    this.idpropriete = idpropriete;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public Polygone getPolygone() {
    return polygone;
  }

  public void setPolygone(Polygone polygone) {
    this.polygone = polygone;
  }

  public double getSuperficie() {
    return superficie;
  }

  public void setSuperficie(double superficie) {
    this.superficie = superficie;
  }

  public double getPerimetre() {
    return perimetre;
  }

  public void setPerimetre(double perimetre) {
    this.perimetre = perimetre;
  }
}
