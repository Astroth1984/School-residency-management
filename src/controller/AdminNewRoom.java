package controller;

import dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminNewRoom")
public class AdminNewRoom extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")!=null  && req.getSession().getAttribute("type").toString().equals("admin"))
            req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
        else
            req.getRequestDispatcher("authentication-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeFloor = req.getParameter("codeEtage");
        String roomId = req.getParameter("roomId");
        int nbResidant = Integer.parseInt(req.getParameter("nb"));
        try{
        if(nbResidant == 1)
        {
            if(ChambreIndivDAO.addChambre(roomId, EtageDAO.getIdEtageByCode(codeFloor)))
            {
                req.setAttribute("success","true");
                req.setAttribute("message","Room created");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
            else
            {
                req.setAttribute("success","false");
                req.setAttribute("message","Error at creating Room");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
        }
        else if(nbResidant ==2)
        {
            if(ChambreA2DAO.addChambre(roomId, EtageDAO.getIdEtageByCode(codeFloor)))
            {
                req.setAttribute("success","true");
                req.setAttribute("message","Room created");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
            else
            {
                req.setAttribute("success","false");
                req.setAttribute("message","Error at creating Room");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
        }
        else if(nbResidant ==3)
        {
            if(ChambreA3DAO.addChambre(roomId, EtageDAO.getIdEtageByCode(codeFloor)))
            {
                req.setAttribute("success","true");
                req.setAttribute("message","Room created");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
            else
            {
                req.setAttribute("success","false");
                req.setAttribute("message","Error at creating Room");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);
            }
        }
        else {
            if (ChambreA4DAO.addChambre(roomId, EtageDAO.getIdEtageByCode(codeFloor))) {
                req.setAttribute("success", "true");
                req.setAttribute("message", "Room created");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req, resp);
            } else {
                req.setAttribute("success", "false");
                req.setAttribute("message", "Error at creating Room");
                req.getRequestDispatcher("adminnewroom.jsp").forward(req, resp);
            }
        }
        }catch (Exception e)
        {
            e.printStackTrace();
            req.setAttribute("success","false");
            req.setAttribute("message","Error at creating Room");
            req.getRequestDispatcher("adminnewroom.jsp").forward(req,resp);

        }
    }
}
