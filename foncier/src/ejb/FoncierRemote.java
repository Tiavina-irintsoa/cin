package ejb;
import jakarta.ejb.Remote;
import sante.FicheFoncier;

@Remote
public interface FoncierRemote {
    public FicheFoncier getFicheFoncier(String idcin) throws Exception;
}
