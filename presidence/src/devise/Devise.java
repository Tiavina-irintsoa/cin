package devise;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Devise {

  int idDevise;
  String nomDevise;

  public static List<Devise> getAllInWS() throws Exception {
    try {
      URL url = new URL(
        "http://127.0.0.1:8080/sante/appli/devise"
      );
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      InputStreamReader reader = new InputStreamReader(
        connection.getInputStream()
      );

      Gson gson = new Gson();
      Type listType = new TypeToken<List<Devise>>() {}.getType();
      List<Devise> listeDeDevises = gson.fromJson(reader, listType);
      connection.disconnect();
      reader.close();
      return listeDeDevises;
    } catch (IOException e) {
      throw e;
    }
  }

 
  public String getNomDevise() {
    return nomDevise;
  }

  public void setNomDevise(String nomDevise) {
    this.nomDevise = nomDevise;
  }


  public int getIdDevise() {
    return idDevise;
  }


  public void setIdDevise(int idDevise) {
    this.idDevise = idDevise;
  }
}
