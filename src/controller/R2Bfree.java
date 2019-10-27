package controller;

import dao.InscriptionDAO;
import model.ChambreA2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/r2bfree")
public class R2Bfree extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("auth") != null && req.getSession().getAttribute("type").toString().equals("student")) {
            try {
                List<ChambreA2> chambres = InscriptionDAO.getA2MaleFreeRooms();
                req.setAttribute("chambres", chambres);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("r2bfree.jsp").forward(req,resp);
        } else
            req.getRequestDispatcher("authentication-login.jsp").forward(req, resp);

    }
}
