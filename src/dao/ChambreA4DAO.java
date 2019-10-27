package dao;

import model.Chambre;
import model.ChambreA3;
import model.ChambreA4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChambreA4DAO {

    public static List<Chambre> getCHambresByBatiment(int idbatiment) throws Exception {
        Connection conn = DBConnect.getConnection();
        List<Chambre> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt * from chambre,chambreA4 WHERE Chambre.IdChambre=ChambreA4.IdChambre and Chambre.IdEtage = (SELECT IdEtage FROM Etage WHERE IdBatiment=?");
        ps.setInt(1,idbatiment);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }

        return chambres;
    }

    public static ChambreA4 getCHambreById(int IdChambre) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt * from chambre,chambreA4 WHERE  Chambre.IdChambre=ChambreA2.IdChambre and Chambre.IdChambre=?");
        ps.setInt(1,IdChambre);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            return (new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }

        return null;
    }


    public static int  isA2Female(String chambreA2) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT * from chambre,chambreA4 WHERE Chambre.IdChambre=ChambreA4.IdChambre and Chambre.CodeChambre = ? and IdEtage IN(Select IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F') )");
        ps.setString(1,chambreA2);
        ResultSet rs =  ps.executeQuery();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return -1;
    }

    public static int isA2Male(String chambreA2) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT * from chambre,chambreA4 WHERE Chambre.IdChambre=ChambreA4.IdChambre and Chambre.CodeChambre = ? and IdEtage IN(Select IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M') )");
        ps.setString(1,chambreA2);
        ResultSet rs =  ps.executeQuery();
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return -1;
    }

    public static boolean addChambre(String CodeChambre,int IdEtage)
    {
        Connection conn=null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement st = conn.prepareStatement("INSERT INTO Chambre(CodeChambre,IdEtage) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,CodeChambre);
            st.setInt(2,IdEtage);
            if(st.executeUpdate()>0)
            {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next())
                {
                    PreparedStatement st2 = conn.prepareStatement("INSERT INTO ChambreA4(EtatPlace1,EtatPlace2,EtatPlace3,EtatPlace4,IdChambre) values (?,?,?,?,?)");
                    st2.setBoolean(1,true);
                    st2.setBoolean(2,true);
                    st2.setBoolean(3,true);
                    st2.setBoolean(4,true);
                    st2.setInt(5,rs.getInt(1));
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

}
