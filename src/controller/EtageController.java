package controller;

import dao.AdminDAO;
import dao.EtageDAO;
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

@WebServlet("/Etage")
public class EtageController extends HttpServlet {

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
            JSONObject dataObject = null;
            if(req.getParameter("IdBatiment")!=null) {
                dataObject = new JSONObject(EtageDAO.getEtagesByBatiment(Integer.parseInt(req.getParameter("IdBatiment"))));
            }
            else
                dataObject = new JSONObject("{\"error\" :\"true\", \"message\" : \"Bad request \"}");
            PrintWriter out = resp.getWriter();
            out.println(dataObject.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        setHeaders(resp);
        JSONObject response = new JSONObject();
        JSONObject jsonDATA = null;
        try {
            jsonDATA = getJSONRequest(req.getReader());
            if(EtageDAO.addNewEtage(jsonDATA.getString("CodeEtage"),jsonDATA.getInt("NBChambres"),jsonDATA.getInt("IdBatiment")))
            {
                response.put("message","admin created successfully");
                response.put("success", "true");
            }
            else
            {
                response.put("message","an error has occurred. please check your informations and retry.");
                response.put("success", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        out.println(response.toString());
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
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
