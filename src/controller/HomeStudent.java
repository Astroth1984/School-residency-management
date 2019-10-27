package controller;

import dao.ClaimDAO;
import model.Claim;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeStudent")
public class HomeStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")!=null && req.getSession().getAttribute("type").toString().equals("student")){
            try {
                List<Claim> claims = ClaimDAO.getAllStudentClaims(((Student) req.getSession().getAttribute("auth")).getId());
                req.setAttribute("claims",claims);
            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher("indexStudent.jsp").forward(req,resp);
        }
        else
            req.getRequestDispatcher("authenticated-login.jsp").forward(req,resp);
    }
}
