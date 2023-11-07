package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendInBanqueServlet extends HttpServlet {

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String sender = req.getParameter("sender");
    String receiver = req.getParameter("receiver");
    String montant = req.getParameter("money");
    String date = req.getParameter("date");
    String devise = req.getParameter("devise");
    String baseUrl = "http://localhost:5251/Transfert/transfert";
    String queryParams =
      "sender=" +
      sender +
      "&receiver=" +
      receiver +
      "&montant=" +
      montant +
      "&iddevise=" +
      devise +
      "&date=" +
      date;
    PrintWriter out = resp.getWriter();
    System.out.println(baseUrl + "?" + queryParams);
    URL url = new URL(baseUrl + "?" + queryParams);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    try {
      // Ouvrez la connexion HTTP

      // Récupérez la réponse
      int responseCode = connection.getResponseCode();
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(connection.getInputStream())
      );
      String line;
      StringBuilder response = new StringBuilder();

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }

      reader.close();
      String responseBody = response.toString();
      out.print(responseBody);
      if(responseBody.length() == 0){
        out.print("Transfert reussi");
      }
      // if (responseCode == HttpURLConnection.HTTP_OK) {
      //   out.print(responseBody);
      //   System.out.println(responseBody);
      // } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
      //   System.err.println("Erreur : " + responseBody);
      // }
      // else {
      //   out.print(responseBody);
      //   System.err.println(
      //     "La requête a échoué avec le code de réponse : " + responseCode
      //   );
      // }
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
