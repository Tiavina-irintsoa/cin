package ejb;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
public class EjbClient {
    
    public Context getContextEjb() throws Exception{
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        Properties properties = new Properties();
        properties.put(
            Context.INITIAL_CONTEXT_FACTORY,
            "org.wildfly.naming.client.WildFlyInitialContextFactory"
        );
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        // Initialisation du contexte JNDI: un service de gestion des nom
        Context context = new InitialContext(properties);
        return context;
    }
}
