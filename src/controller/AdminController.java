package controller;

import dao.AdminDAO;
import dao.StudentDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setHeaders(resp);
        try {
            JSONArray data = null;
            JSONObject dataObject = null;
            boolean flag = false;
            if(req.getParameter("Immatricule")!=null) {
                dataObject = new JSONObject(AdminDAO.getAdminByMatricule(Integer.parseInt(req.getParameter("Immatricule"))));
                flag = true;
            }
            else if(req.getParameter("CINUser")!=null){
                dataObject = new JSONObject(AdminDAO.getAdminByCIN(req.getParameter("CINUser")).toString());
                flag = true;
            }
            else if(req.getParameter("IdAdmin")!=null) {
                dataObject = new JSONObject(AdminDAO.getAdminByIdAdmin(Integer.parseInt(req.getParameter("IdAdmin"))).toString());
                flag = true;
            }
            else if(req.getParameter("IdUser")!=null) {
                dataObject = new JSONObject(AdminDAO.getAdminByIdUser(Integer.parseInt(req.getParameter("IdUser"))).toString());
                flag = true;
            }
            else
                data = new JSONArray(AdminDAO.getAllAdmins());
            PrintWriter out = resp.getWriter();
            if(flag)
                out.println(dataObject.toString());
            else
                out.println(data.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setHeaders(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setHeaders(resp);
        JSONObject response = new JSONObject();
        JSONObject jsonDATA = null;
        try {
            jsonDATA = getJSONRequest(req.getReader());
            if(AdminDAO.addNewAdmin(jsonDATA.get("nameUser").toString(),jsonDATA.get("EmailUser").toString(),jsonDATA.get("phoneUser").toString(),jsonDATA.get("CINUser").toString(),jsonDATA.getInt("Immatricule"),jsonDATA.getString("passwordUser")))
            {
                response.put("message","admin created successfully");
                response.put("success", "true");
            }
            else
            {
                response.put("message","an error has occurred. please check your informations and retry.");
                response.put("success", "false");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        out.println(response.toString());
        out.flush();
        System.out.println(jsonDATA.toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setHeaders(resp);
        JSONObject jsonDATA = null;
        try{
            jsonDATA = getJSONRequest(req.getReader());
            if(jsonDATA.has("CINUser"))
            {
                AdminDAO.updateAdminCIN(jsonDATA.getString("CINUser"),jsonDATA.getInt("IdUser"));
            }
            else if(jsonDATA.has("phoneUser"))
            {
                AdminDAO.updateAdminPhone(jsonDATA.getString("phoneUser"),jsonDATA.getInt("IdUser"));
            }
            else if(jsonDATA.has("EmailUser"))
            {
                AdminDAO.updateAdminMail(jsonDATA.getString("EmailUser"),jsonDATA.getInt("IdUser"));
            }
            else if(jsonDATA.has("passwordUser"))
            {
                AdminDAO.updateAdminPassword(jsonDATA.getString("passwordUser"),jsonDATA.getInt("IdUser"));
            }
            else if(jsonDATA.has("Immatricule"))
            {
                AdminDAO.updateAdminImmatricule(jsonDATA.getInt("CNE"),jsonDATA.getInt("IdUser"));
            }
            else if(jsonDATA.has("nameUser"))
            {
                AdminDAO.updateAdminName(jsonDATA.getString("nameUser"),jsonDATA.getInt("IdUser"));
            }
            JSONObject response = new JSONObject(AdminDAO.getAdminByIdUser(jsonDATA.getInt("IdUser")));
            PrintWriter out = resp.getWriter();
            out.println(response.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

 

    private JSONObject getJSONRequest(BufferedReader reader) throws IOException,JSONException
    {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return new JSONObject(buffer.toString());
    }
}
