package controller;

import dao.AdminDAO;
import dao.ClaimDAO;
import dao.InscriptionDAO;
import dao.StudentDAO;
import model.Admin;
import model.Claim;
import model.Data;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = null;
        try {
            password = toSHA1(request.getParameter("password").getBytes());
            System.out.println(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            Student std = StudentDAO.studentLogin(email,password);
            if(std !=null)
            {
                System.out.println("authenticated");
                List<Claim> claims = ClaimDAO.getAllStudentClaims(std.getId());
                request.getSession().setAttribute("auth", std);
                request.getSession().setAttribute("type", "student");
                request.getSession().setAttribute("authenticated", true);
                request.setAttribute("claims",claims);
                request.getRequestDispatcher("indexStudent.jsp").forward(request,response);
            }
            else
            {
                Admin admin = AdminDAO.adminLogin(email,password);
                if(admin != null)
                {
                    System.out.println("authenticated");
                    request.getSession().setAttribute("auth", admin);
                    request.getSession().setAttribute("type", "admin");
                    request.getSession().setAttribute("authenticated", true);
                    List<Data> data = null;
                    List<Claim> claims = null;
                    try {
                        data = InscriptionDAO.getLatestReservations();
                        claims = ClaimDAO.getLatestClaims();
                        System.out.println(claims == null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    request.setAttribute("data",data);
                    request.setAttribute("claims",claims);
                    request.getRequestDispatcher("indexAdmin.jsp").forward(request,response);

                }
                else
                {
                    request.setAttribute("error", "Erreur d'authentification");
                    request.getRequestDispatcher("authentication-login.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("auth")==null)
            request.getRequestDispatcher("authentication-login.jsp").forward(request,response);
        else
        {
            if(request.getSession().getAttribute("type").equals("student"))
                request.getRequestDispatcher("indexStudent.jsp").forward(request,response);
            else if(request.getSession().getAttribute("type").equals("student"))
                response.sendRedirect("./HomeStudent");

        }
    }

    public static String toSHA1(byte[] convertme) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(convertme);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
