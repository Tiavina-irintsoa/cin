package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FormulaireServlet extends HttpServlet {

  protected void processRequest(
    HttpServletRequest request,
    HttpServletResponse resp
  ) throws ServletException, IOException {
    try {
      String[] latitudes = request.getParameterValues("latitude");
      String[] longitudes = request.getParameterValues("longitude");
      String description = request.getParameter("description");
      String adresse = request.getParameter("adresse");
      String idcin = request.getParameter("idcin");
      JsonObject json = new JsonObject();
      JsonArray proprietes = new JsonArray();
      JsonObject propriete = new JsonObject();
      proprietes.add(propriete);
      propriete.addProperty("description", description);
      propriete.addProperty("adresse", adresse);

      JsonObject polygone = new JsonObject();
      JsonArray coordonnees = new JsonArray();
      polygone.add("coordonnees", coordonnees);
      for (int i = 0; i < latitudes.length; i++) {
        JsonObject coordonnee = new JsonObject();
        coordonnee.addProperty("lat", latitudes[i]);
        coordonnee.addProperty("lng", longitudes[i]);
        coordonnees.add(coordonnee);
      }
      JsonObject citoyen = new JsonObject();
      citoyen.addProperty("idcin", idcin);

      JsonObject fiche = new JsonObject();
      fiche.add("citoyen", citoyen);
      fiche.add("proprietes", proprietes);

      Gson gson = new Gson();
      String jsonString = gson.toJson(fiche);
      System.out.println(jsonString);
      sendWS(jsonString);
      //   resp.sendRedirect("data");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void sendWS(String jsonData) throws Exception {
    String serviceUrl = "http://127.0.0.1:8080/foncier/appli/propriete";
    URL url = new URL(serviceUrl);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);
    try (OutputStream os = connection.getOutputStream()) {
      byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
      os.write(input, 0, input.length);
    }
    connection.disconnect();
  }

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    processRequest(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    processRequest(req, resp);
  }
}
