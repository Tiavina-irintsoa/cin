package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import presidence.CitoyenData;

public class AfficherInformationsServlet extends HttpServlet {

  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    try {
      String idcin = req.getParameter("idcin");
      CitoyenData data = new CitoyenData(idcin);
      data.complete();
      req.setAttribute("data", data);
      RequestDispatcher dispatcher = req.getRequestDispatcher("data.jsp");
      dispatcher.forward(req, resp);
    } catch (Exception e) {
      PrintWriter out = resp.getWriter();
      out.print(e.getMessage());
      e.printStackTrace();
    }
  }
}
