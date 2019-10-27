package dao;

import model.Chambre;
import model.ChambreIndividuelle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreIndivDAO {

    public static List<Chambre> getCHambresByBatiment(int idbatiment) throws Exception {
        Connection conn = DBConnect.getConnection();
        List<Chambre> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt * from chambre,chambreindiv WHERE Chambre.IdEtage = (SELECT IdEtage FROM Etage WHERE IdBatiment=?)");
        ps.setInt(1,idbatiment);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreIndividuelle(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5)));
        }

        return chambres;
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
                    PreparedStatement st2 = conn.prepareStatement("INSERT INTO ChambreIndiv(EtatChambre,IdChambre) values (?,?)");
                    st2.setBoolean(1,true);
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



}
