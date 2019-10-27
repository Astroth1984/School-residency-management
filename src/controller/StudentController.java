package controller;

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

@WebServlet("/Student")
public class StudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        setHeaders(resp);
        try {
            JSONArray data = null;
            JSONObject dataObject = null;
            boolean flag = false;
            if(req.getParameter("CNE")!=null) {
                dataObject = new JSONObject(StudentDAO.getStudentByCNE(req.getParameter("CNE")).toString());
                flag = true;
            }
            else if(req.getParameter("CINUser")!=null){
                dataObject = new JSONObject(StudentDAO.getStudentByCIN(req.getParameter("CINUser")).toString().toString());
                flag = true;
            }
            else if(req.getParameter("IdStudent")!=null) {
                dataObject = new JSONObject(StudentDAO.getStudentByIdStudent(Integer.parseInt(req.getParameter("IdStudent"))).toString());
                flag = true;
            }
            else if(req.getParameter("IdUser")!=null) {
                dataObject = new JSONObject(StudentDAO.getStudentByIdUser(Integer.parseInt(req.getParameter("IdUser"))).toString());
                flag = true;
            }
            else
                data = new JSONArray(StudentDAO.getAllStudent());
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
            if(StudentDAO.addNewStudent(jsonDATA.get("nameUser").toString(),jsonDATA.get("EmailUser").toString(),jsonDATA.get("phoneUser").toString(),jsonDATA.get("CINUser").toString(),jsonDATA.get("Level").toString(),jsonDATA.get("CNE").toString(),jsonDATA.getString("passwordUser")))
            {
                response.put("message","student created successfully");
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        setHeaders(resp);
        JSONObject jsonDATA = null;
        try{
                jsonDATA = getJSONRequest(req.getReader());
                if(jsonDATA.has("CINUser"))
                {
                    StudentDAO.updateStudentCIN(jsonDATA.getString("CINUser"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("phoneUser"))
                {
                    StudentDAO.updateStudentPhone(jsonDATA.getString("phoneUser"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("EmailUser"))
                {
                    StudentDAO.updateStudentMail(jsonDATA.getString("EmailUser"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("passwordUser"))
                {
                    StudentDAO.updateStudentPassword(jsonDATA.getString("passwordUser"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("CNE"))
                {
                    StudentDAO.updateStudentCNE(jsonDATA.getString("CNE"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("Level"))
                {
                    StudentDAO.updateStudentLevel(jsonDATA.getString("Level"),jsonDATA.getInt("IdUser"));
                }
                else if(jsonDATA.has("nameUser"))
                {
                    StudentDAO.updateStudentName(jsonDATA.getString("nameUser"),jsonDATA.getInt("IdUser"));
                }
                JSONObject response = new JSONObject(StudentDAO.getStudentByIdUser(jsonDATA.getInt("IdUser")));
                PrintWriter out = resp.getWriter();
                out.println(response.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // we don't have right to delete a student.
        setHeaders(resp);
        //SEND 403 FORBIDDEN STATUS
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        JSONObject jo = new JSONObject();
        try {
            jo.put("status","403 FORBIDDEN");
            jo.put("message" , " students cannot be deleted");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        out.println(jo.toString());
        out.flush();

    }

    /**
     *
     * @param reader
     * @return JSONObject
     * @throws IOException
     * @throws JSONException
     */
    private JSONObject getJSONRequest(BufferedReader reader) throws IOException,JSONException
    {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return new JSONObject(buffer.toString());
    }

    private static void setHeaders(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        resp.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
    }

}
