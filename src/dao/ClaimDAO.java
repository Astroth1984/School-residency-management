package dao;

import model.Claim;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {

    public static List<Claim> getAllClaims() throws Exception {
        List<Claim> list = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdClaim,Categorie,Contenu,dateClaim,valide,IdUser FROM Claims Where valide=false");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Student st = StudentDAO.getStudentByIdUser(rs.getInt(6));
            list.add(new Claim(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5),st));
        }
        return list;
    }

    public static List<Claim> getAllStudentClaims(int idstudent) throws Exception {
        List<Claim> list = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdClaim,Categorie,Contenu,dateClaim,valide,IdUser FROM Claims Where IdUser=(SElect IdUSer From Student Where IdStudent=?)");
        ps.setInt(1,idstudent);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Student st = StudentDAO.getStudentByIdUser(rs.getInt(6));
            list.add(new Claim(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5),st));
        }
        return list;
    }

    public static List<Claim> getLatestClaims() throws Exception {
        List<Claim> list = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdClaim,Categorie,Contenu,dateClaim,valide,IdUser FROM Claims where valide=false ORDER BY dateclaim DESC LIMIT 5");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Student st = StudentDAO.getStudentByIdUser(rs.getInt(6));
            list.add(new Claim(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5),st));
        }
        return list;
    }

    public static Claim getClaimById(int id) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdClaim,Categorie,Contenu,dateClaim,valide,IdUser FROM Claims where valide=false and IdClaim=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            Student st = StudentDAO.getStudentByIdUser(rs.getInt(6));
            return (new Claim(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getBoolean(5),st));
        }
        return null;
    }

    public static boolean validate(int id) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("UPDATE Claims SET valide=true WHERE IdClaim=?");
        ps.setInt(1,id);
        return ps.executeUpdate()>0;
    }
    public static boolean insert(String cat,String text,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Claims (CAtegorie,Contenu,IdUser) values(?,?,?)");
        ps.setString(1,cat);
        ps.setString(2,text);
        ps.setInt(3,IdUser);
        return ps.executeUpdate()>0;
    }

    public static boolean delete(int id) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Claims WHERE IdClaim=?");
        ps.setInt(1,id);
        return ps.executeUpdate()>0;
    }
}
