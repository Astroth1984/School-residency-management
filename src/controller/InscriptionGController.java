package controller;

import dao.ChambreA2DAO;
import dao.ChambreA3DAO;
import dao.ChambreA4DAO;
import dao.InscriptionDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reserveMale")
public class InscriptionGController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
            String roomID = req.getParameter("RoomID");
            int delay = Integer.parseInt(req.getParameter("Delay"));
        try {
            if(ChambreA2DAO.isA2Male(roomID)!=-1)
            {
                if(InscriptionDAO.reserverChambreA2((Student) req.getSession().getAttribute("auth"), ChambreA2DAO.getCHambreById(ChambreA2DAO.isA2Male(roomID)),delay))
                {
                    req.setAttribute("message","Room reserved successfully");
                    req.setAttribute("success","true");

                }
                else
                {
                    req.setAttribute("message","Room cannot be reserved");
                    req.setAttribute("success","false");
                }
            }
            else if(ChambreA3DAO.isA3Male(roomID)!=-1)
            {
                if(InscriptionDAO.reserverChambreA3((Student) req.getSession().getAttribute("auth"), ChambreA3DAO.getCHambreById(ChambreA3DAO.isA3Male(roomID)),delay))
                {
                    req.setAttribute("message","Room reserved successfully");
                    req.setAttribute("success","true");
                }
                else
                {
                    req.setAttribute("message","Room cannot be reserved");
                    req.setAttribute("success","false");
                }
            }
            else if(ChambreA4DAO.isA2Male(roomID)!=-1)
            {
                if(InscriptionDAO.reserverChambreA4((Student) req.getSession().getAttribute("auth"), ChambreA4DAO.getCHambreById(ChambreA4DAO.isA2Male(roomID)),delay))
                {
                    req.setAttribute("message","Room reserved successfully");
                    req.setAttribute("success","true");
                }
                else
                {
                    req.setAttribute("message","Room cannot be reserved");
                    req.setAttribute("success","false");
                }
            }
            else
            {
                req.setAttribute("message","Room doesn't exist");
                req.setAttribute("success","false");
            }
            req.getRequestDispatcher("girlsDorms.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }
}
