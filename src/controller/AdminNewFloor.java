package controller;

import dao.BatimentDAO;
import dao.EtageDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminNewFloor")
public class AdminNewFloor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")!=null  && req.getSession().getAttribute("type").toString().equals("admin"))
            req.getRequestDispatcher("adminnewfloor.jsp").forward(req,resp);
        else
            req.getRequestDispatcher("authentication-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        int nbrooms = Integer.parseInt(req.getParameter("roomNumber"));
        String gender = req.getParameter("gender");
        String codeBuilding = req.getParameter("codeBuilding");
        try {
            if(EtageDAO.addNewEtage(code,nbrooms, BatimentDAO.getIdBatimentByCodeByGender(codeBuilding,gender)))
            {
                req.setAttribute("success","true");
                req.setAttribute("message","Floor created");
                req.getRequestDispatcher("adminnewfloor.jsp").forward(req,resp);
            }
            else
            {
                req.setAttribute("success","false");
                req.setAttribute("message","Error at creating floor");
                req.getRequestDispatcher("adminnewfloor.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("success","false");
            req.setAttribute("message","Error at creating floor");
            req.getRequestDispatcher("adminnewfloor.jsp").forward(req,resp);
        }
    }
}
