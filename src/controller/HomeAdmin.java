package controller;

import dao.ClaimDAO;
import dao.InscriptionDAO;
import model.Claim;
import model.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeAdmin")
public class HomeAdmin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")!=null  && req.getSession().getAttribute("type").toString().equals("admin")) {
            List<Data> data = null;
            List<Claim> claims = null;
            try {
                data = InscriptionDAO.getLatestReservations();
                claims = ClaimDAO.getLatestClaims();
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.setAttribute("data",data);
            req.setAttribute("claims",claims);
            req.getRequestDispatcher("indexAdmin.jsp").forward(req, resp);
        }
        else
            req.getRequestDispatcher("authentication-login.jsp").forward(req, resp);
    }
}
