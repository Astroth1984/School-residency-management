package controller;

import dao.ClaimDAO;
import model.Student;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/claims")
public class ClaimsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("studentclaim.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String categories = req.getParameter("categories");
        String roomid = req.getParameter("roomid");
        String message = req.getParameter("message");

        try {
            ClaimDAO.insert(categories,message,((Student)req.getSession().getAttribute("auth")).getIdUser());
            resp.sendRedirect("./HomeStudent");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
