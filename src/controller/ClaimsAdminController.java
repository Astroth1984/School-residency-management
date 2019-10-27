package controller;

import dao.ClaimDAO;
import model.Claim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ClaimsAdmin")
public class ClaimsAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")!=null  && req.getSession().getAttribute("type").toString().equals("admin")) {
            if(req.getParameter("view")==null && req.getParameter("validate")==null && req.getParameter("delete")==null)
            {
                List<Claim> claims = null;
                try {
                    claims = ClaimDAO.getAllClaims();
                    req.setAttribute("claims",claims);
                } catch (Exception e) {
                    e.printStackTrace();
                    claims = new ArrayList<>();
                    req.setAttribute("claims",claims);
                }
                req.getRequestDispatcher("adminclaims.jsp").forward(req, resp);
            }
            else if(req.getParameter("view")!=null)
            {
                try {
                    Claim claim = ClaimDAO.getClaimById(Integer.parseInt(req.getParameter("view")));
                    req.setAttribute("claim",claim);
                    req.getRequestDispatcher("adminclaim.jsp").forward(req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(req.getParameter("validate")!=null)
            {
                try {
                    if(ClaimDAO.validate(Integer.parseInt(req.getParameter("validate"))))
                    {
                        req.setAttribute("success","true");
                        req.setAttribute("message","Claim validated");
                        List<Claim> claims = null;
                        try {
                            claims = ClaimDAO.getAllClaims();
                            req.setAttribute("claims",claims);
                        } catch (Exception e) {
                            e.printStackTrace();
                            claims = new ArrayList<>();
                            req.setAttribute("claims",claims);
                        }
                        req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
                    }
                    else{
                        req.setAttribute("success","false");
                        req.setAttribute("message","Error occured. please retry!");
                        List<Claim> claims = null;
                        try {
                            claims = ClaimDAO.getAllClaims();
                            req.setAttribute("claims",claims);
                        } catch (Exception e) {
                            e.printStackTrace();
                            claims = new ArrayList<>();
                            req.setAttribute("claims",claims);
                        }
                        req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("success","false");
                    req.setAttribute("message","Error occured. please retry!");
                    List<Claim> claims = null;
                    try {
                        claims = ClaimDAO.getAllClaims();
                        req.setAttribute("claims",claims);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        claims = new ArrayList<>();
                        req.setAttribute("claims",claims);
                    }
                    req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
                }
            }
            else if(req.getParameter("delete")!=null)
            {
                try {
                if(ClaimDAO.delete(Integer.parseInt(req.getParameter("delete"))))
                {
                    req.setAttribute("success","true");
                    req.setAttribute("message","Claim deleted");
                    List<Claim> claims = null;
                    try {
                        claims = ClaimDAO.getAllClaims();
                        req.setAttribute("claims",claims);
                    } catch (Exception e) {
                        e.printStackTrace();
                        claims = new ArrayList<>();
                        req.setAttribute("claims",claims);
                    }
                    req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
                }
                else{
                    req.setAttribute("success","false");
                    req.setAttribute("message","Error occured. please retry!");
                    List<Claim> claims = null;
                    try {
                        claims = ClaimDAO.getAllClaims();
                        req.setAttribute("claims",claims);
                    } catch (Exception e) {
                        e.printStackTrace();
                        claims = new ArrayList<>();
                        req.setAttribute("claims",claims);
                    }
                    req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
                    req.setAttribute("success","false");
                    req.setAttribute("message","Error occured. please retry!");
                    List<Claim> claims = null;
                    try {
                        claims = ClaimDAO.getAllClaims();
                        req.setAttribute("claims",claims);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        claims = new ArrayList<>();
                        req.setAttribute("claims",claims);
                    }
                    req.getRequestDispatcher("adminclaims.jsp").forward(req,resp);
            }
        }

        }
    }
}
