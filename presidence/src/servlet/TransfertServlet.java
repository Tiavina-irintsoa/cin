package servlet;

import java.io.IOException;
import java.util.List;

import devise.Devise;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TransfertServlet extends HttpServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Devise> listeDevises = Devise.getAllInWS();
            req.setAttribute("liste", listeDevises);
            RequestDispatcher dispat = req.getRequestDispatcher("transfert.jsp");
            dispat.forward(req,resp);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
