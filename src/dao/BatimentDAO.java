package dao;

import model.Batiment;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BatimentDAO {

    public static List<Batiment> getAllBatiments() throws Exception {
        return getBatiments("SELECT IdBatiment,CodeBatiment, Gender,NBEtage FROM Batiment");
    }

    public static int getIdBatimentByCodeByGender(String code,String gender) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdBatiment FROM Batiment WHERE CodeBatiment=? and gender=?");
        ps.setString(1,code);
        ps.setString(2,gender);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        return 1;
    }

    public static List<Batiment> getBatimentsMales() throws Exception {
        return getBatiments("SELECT IdBatiment, CodeBatiment, Gender,NBEtage FROM Batiment WHERE Gender='MALE'");
    }

    public static List<Batiment> getBatimentsFemales() throws Exception {
        return getBatiments("SELECT IdBatiment,CodeBatiment, Gender,NBEtage FROM Batiment WHERE Gender='FEMALE'");
    }

    private static List<Batiment> getBatiments(String s) throws Exception {
        List<Batiment> batiments = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(s);
        while(rs.next())
        {
            batiments.add(new Batiment(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(3)));
        }
        return batiments;
    }

    public static boolean addNewBatiment(String gender,int NBEtages,String code) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Batiment (CodeBatiment,Gender,NBEtage) VALUES(?,?,?)");
        ps.setString(1,code);
        ps.setString(2,gender);
        ps.setInt(3,NBEtages);
        return ps.executeUpdate()>0;
    }

    public static boolean updateBatiment(String gender,int NBEtages,String code,int IdBatiment) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("UPDATE Batiment SET Gender=?,NBEtage=?,CodeBatiment=? WHERE IdBatiment=?");
        ps.setString(1,gender);
        ps.setString(3,code);
        ps.setInt(2,NBEtages);
        ps.setInt(4,IdBatiment);
        return ps.executeUpdate()>0;
    }


    public static Batiment getBatimentByCode(String code) throws Exception {
        Batiment batiment = null;
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt IdBatiment, CodeBatiment, Gender,NBEtage FROM Batiment WHERE CodeBatiment=?");
        ps.setString(1,code);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
           batiment = new Batiment(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(3));
        }
        return batiment;
    }

    public static Batiment getBatimentById(int IdBatiment) throws Exception {
        Batiment batiment = null;
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdBatiment, CodeBatiment, Gender,NBEtage FROM Batiment WHERE IdBatiment=?");
        ps.setInt(1,IdBatiment);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
           batiment = new Batiment(rs.getInt("IdBatiment"), rs.getString("Gender"),rs.getString("CodeBatiment"), rs.getInt("NBEtage"));
        }
        return batiment;
    }

    public static Batiment getBatimentByIdByGender(int IdBatiment,String gender) throws Exception {
        Batiment batiment = new Batiment();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT IdBatiment, CodeBatiment, Gender,NBEtage FROM Batiment WHERE IdBatiment=? and gender=?");
        ps.setInt(1,IdBatiment);
        ps.setString(2,gender);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            batiment = new Batiment(rs.getInt("IdBatiment"), rs.getString("Gender"),rs.getString("CodeBatiment"), rs.getInt("NBEtage"));
        }
        return batiment;
    }
}
