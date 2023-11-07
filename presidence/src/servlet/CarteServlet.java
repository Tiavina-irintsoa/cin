package servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@WebServlet("/carte_ajout")
public class CarteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  protected void deGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {}

  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    response.setContentType("text/html");
    java.io.PrintWriter out = response.getWriter();
    try {
      HttpSession session = request.getSession();
      String adresse = request.getParameter("adresse");
      String idcin = request.getParameter("idcin");
      String description = request.getParameter("descrption");
      String coordonne = (String) session.getAttribute("coordonnee");

      out.println(coordonne);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void sendRequest(String jsonData) throws Exception {
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
}
