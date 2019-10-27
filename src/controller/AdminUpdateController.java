package controller;

import dao.ChambreA2DAO;
import dao.InscriptionDAO;
import model.ChambreA2;
import model.Inscription;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminUpdate")
public class AdminUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("adminupdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomId = req.getParameter("roomId");
        String gender = req.getParameter("gender");
        try {
            int idchambre = ChambreA2DAO.getIdChambreByGenderByCode(gender,roomId);
            List<Student> students = InscriptionDAO.getRoomResident(idchambre);
            req.setAttribute("students",students);
            req.getRequestDispatcher("adminupdateroom.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
