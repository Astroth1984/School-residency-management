package controller;

import dao.AdminDAO;
import dao.BatimentDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Batiment")
public class BatimentController extends HttpServlet {

    private static void setHeaders(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        setHeaders(resp);
        try {
            JSONArray data = null;
            JSONObject dataObject = null;
            boolean flag = false;
            if(req.getParameter("gender")!=null) {
                if(req.getParameter("gender").equals("FEMALE"))
                    dataObject = new JSONObject(BatimentDAO.getBatimentsFemales().toString());
                else
                    dataObject = new JSONObject(BatimentDAO.getBatimentsMales().toString());
                flag = true;
            }
            else if(req.getParameter("code")!=null){
                dataObject = new JSONObject(BatimentDAO.getBatimentByCode(req.getParameter("CodeBatiment").toString()));
                flag = true;
            }
            else
                data = new JSONArray(BatimentDAO.getAllBatiments());
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        setHeaders(resp);
        JSONObject response = new JSONObject();
        JSONObject jsonDATA = null;
        try {
            jsonDATA = getJSONRequest(req.getReader());
            if(BatimentDAO.addNewBatiment(jsonDATA.getString("gender"),jsonDATA.getInt("NBEtage"),jsonDATA.getString("CodeBatiment")))
            {
                response.put("message","building created successfully");
                response.put("success", "true");
            }
            else
            {
                response.put("message","an error has occurred. please check your informations and retry.");
                response.put("success", "false");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        out.println(response.toString());
        out.flush();
        System.out.println(jsonDATA.toString());
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
