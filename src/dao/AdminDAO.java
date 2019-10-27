package dao;

import model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    public static List<Admin> getAllAdmins() throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        ArrayList<Admin> admins = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser");
        while(rs.next())
        {
            admins.add(new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7)));
        }
        return admins;
    }


    public static Admin getAdminByCIN(String cin) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and AppUser.cinUser=?");
        st.setString(1,cin);
        return getAdmin(st);
    }


    public static Admin getAdminByIdUser(int iduser) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and AppUser.IdUSer=?");
        st.setInt(1,iduser);
        return getAdmin(st);
    }


    public static Admin getAdminByIdAdmin(int IdAdmin) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and AppUser.IdAdmin=?");
        st.setInt(1,IdAdmin);
        return getAdmin(st);
    }


    public static Admin getAdminByMatricule(int immatricule) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin,CNE FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and Administrator.ImmatriculeAdmin=?");
        st.setInt(1,immatricule);
        return getAdmin(st);
    }


    public static Admin getAdminByEmail(String email) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and AppUser.EmailUser=?");
        st.setString(1,email);
        return getAdmin(st);
    }


    public static Admin adminLogin(String email,String password) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdAdmin,ImmatriculeAdmin FROM AppUser,Administrator WHERE AppUser.IdUser = Administrator.IdUser and AppUser.emailuser=? and AppUser.passwordUser=?");
        st.setString(1,email);
        st.setString(2,password);
        return getAdmin(st);
    }


    private static Admin getAdmin(PreparedStatement st) throws SQLException {
        Admin admin = null;
        ResultSet rs = st.executeQuery();
        if(rs.next())
        {
            admin = new Admin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
        }
        return admin;
    }


    public static boolean addNewAdmin(String nameUser,String EmailUser,String PhoneUser, String CINUser,int immatricule, String passwordUser)
    {
        Connection conn=null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement st = conn.prepareStatement("INSERT INTO AppUser(nameUser, EmailUser, PhoneUser, CINUser, passwordUser) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            st.setString(1,nameUser);
            st.setString(2,EmailUser);
            st.setString(3,PhoneUser);
            st.setString(4,CINUser);
            st.setString(5,passwordUser);

            if(st.executeUpdate()>0)
            {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next())
                {
                    PreparedStatement st2 = conn.prepareStatement("INSERT INTO Administrator(ImmatriculeAdmin,idUser) values (?,?)");
                    st2.setInt(1,immatricule);
                    st2.setInt(2,rs.getInt(1));
                    if(st2.executeUpdate()>0) {
                        conn.commit();
                        return true;
                    }
                }
            }
        }catch(Exception e)
        {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }


    public static boolean updateAdminCIN(String cin,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET CIN=? WHERE IdUser=?");
        return executeUpdate(st,cin,IdUser);
    }


    public static boolean updateAdminPhone(String phone,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET phoneUser=? WHERE IdUser=?");
        return executeUpdate(st,phone,IdUser);
    }


    public static boolean updateAdminMail(String mail,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET emailUser=? WHERE IdUser=?");
        return executeUpdate(st,mail,IdUser);
    }


    public static boolean updateAdminName(String name,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET nameUser=? WHERE IdUser=?");
        return executeUpdate(st,name,IdUser);
    }


    public static boolean updateAdminPassword(String password,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET passwordUSer=? WHERE IdUser=?");
        return executeUpdate(st,password,IdUser);
    }




    public static boolean updateAdminImmatricule(int immatricule,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE Admin SET ImmatriculeAdmin=? WHERE IdUser=?");
        st.setInt(1,immatricule);
        st.setInt(2,IdUser);

        return st.executeUpdate()>0;
    }

    private static boolean executeUpdate(PreparedStatement st,String param1,int param2) throws Exception
    {
        st.setString(1,param1);
        st.setInt(2,param2);

        return st.executeUpdate()>0;
    }
}
