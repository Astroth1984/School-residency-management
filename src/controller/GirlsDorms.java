package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GirlsDorms")
public class GirlsDorms  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("auth")==null)
        req.getRequestDispatcher("authentication-login.jsp").forward(req,resp);
        else
        req.getRequestDispatcher("girlsDorms.jsp").forward(req,resp);
    }
}
