package controller;

import dao.BatimentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminNewBuilding")
public class AdminNewBuilding extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("adminnewbuilding.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("Code");
        String Gender = req.getParameter("gender");
        int floornb = Integer.parseInt(req.getParameter("floorNb"));
        try {
            if(BatimentDAO.addNewBatiment(Gender,floornb,code))
            {
                req.setAttribute("success","true");
                req.setAttribute("message","Building created");
                req.getRequestDispatcher("adminnewbuilding.jsp").forward(req,resp);
            }
            else{
                req.setAttribute("success","false");
                req.setAttribute("message","Error occured at building creation. Please retry !");
                req.getRequestDispatcher("adminnewbuilding.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("success","false");
            req.setAttribute("message","Error occured at building creation. Please retry !");
            req.getRequestDispatcher("adminnewbuilding.jsp").forward(req,resp);
        }
    }
}
