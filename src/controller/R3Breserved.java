package controller;

import dao.InscriptionDAO;
import model.ChambreA3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/r3breserved")
public class R3Breserved extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("auth") != null && req.getSession().getAttribute("type").toString().equals("student")) {
            try {
                List<ChambreA3> chambres = InscriptionDAO.getA3MaleFreeRooms();
                req.setAttribute("chambres", chambres);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("r3breserved.jsp").forward(req,resp);
        } else
            req.getRequestDispatcher("authentication-login.jsp").forward(req, resp);
    }
}
