package controller;

import dao.InscriptionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteReserv")

public class AdminDeleteReservController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            InscriptionDAO.unvalidate(id);
            resp.sendRedirect("./AdminUpdate");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
