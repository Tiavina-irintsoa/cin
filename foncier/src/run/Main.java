package run;

import ejb.EjbRemote;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import sante.Citoyen;

public class Main {

  public static void main(String[] args) {
    try {
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");

      Properties properties = new Properties();
      properties.put(
        Context.INITIAL_CONTEXT_FACTORY,
        "org.wildfly.naming.client.WildFlyInitialContextFactory"
      );
      properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
      // Initialisation du contexte JNDI: un service de gestion des nom
      Context context = new InitialContext(properties);
      // Recherche de l'EJB en utilisant le nom JNDI
      String jndiName = "ejb:sante/sante.jar/EjbCitoyen!ejb.EjbRemote"; // le nom JNDI
      EjbRemote remote = (EjbRemote) context.lookup(jndiName);

      // Appel de méthodes de l'EJB
      Citoyen result = remote.getCitoyen("CIN001");
      System.out.println(result);
      context.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
