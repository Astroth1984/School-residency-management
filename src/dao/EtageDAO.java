package dao;

import model.Etage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtageDAO {


    public static List<Etage> getEtagesByBatiment(int idbatiment) throws Exception {
        List<Etage> etages = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt * FROM Etage WHERE IdBatiment=?");
        ps.setInt(1,idbatiment);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            etages.add(new Etage(rs.getInt(1),rs.getString(2),rs.getInt(3),BatimentDAO.getBatimentById(idbatiment)));
        }
        return null;
    }

    public static Etage getEtageById(int id) throws Exception {
        Etage etage = null;
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt IdEtage,Codeetage,NbChambres,IdBatiment FROM Etage WHERE IdEtage=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            etage = new Etage(rs.getInt("IdEtage"),rs.getString("Codeetage"),rs.getInt("NbChambres"),BatimentDAO.getBatimentById(rs.getInt("IdBatiment")));
        return etage;
    }

    public static Etage getEtageByIdByGender(int id,String gender) throws Exception {
        Etage etage = null;
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt IdEtage,Codeetage,NbChambres,IdBatiment FROM Etage WHERE IdEtage=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            etage = new Etage(rs.getInt("IdEtage"),rs.getString("Codeetage"),rs.getInt("NbChambres"),BatimentDAO.getBatimentByIdByGender(rs.getInt("IdBatiment"),gender));
        return etage;
    }

        public static boolean addNewEtage(String codeEtage,int NBChambres,int idBatiment) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Etage (CodeEtage,NbChambres,IdBatiment) VALUES(?,?,?)");
        ps.setString(1,codeEtage);
        ps.setInt(2,NBChambres);
        ps.setInt(3,idBatiment);
        return ps.executeUpdate()>0;
    }


    public static Integer getIdEtageByCode(String codeFloor) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt IdEtage FROM Etage WHERE CodeEtage=?");
        ps.setString(1,codeFloor);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getInt(1);
        return null;
    }
}
