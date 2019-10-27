package dao;

import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDAO {

    public static List<Student> getRoomResident(int idchambre) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,Student.IdStudent,Level,CNE FROM AppUser,Student,Inscription WHERE AppUser.IdUser = Student.IdUser and Inscription.IdChambre=? and  inscription.IdStudent=Student.IdStudent and  Inscription.current=true");
        st.setInt(1,idchambre);
        List<Student> student = new ArrayList<>();
        ResultSet rs = st.executeQuery();
        while(rs.next())
        {
            student.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
        }
        return student;

    }

    public static boolean unvalidate(int idStudent) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE Inscription SET current=false WHERE IdStudent=? and current=true");
        st.setInt(1,idStudent);
        return st.executeUpdate()>0;
    }

    public static boolean reserverChambreIndiv(Student std, ChambreIndividuelle chambre,int duree) {
        if (!chambre.isEtat())
            return false;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(true);
            PreparedStatement ps = conn.prepareStatement("UPDATE ChambreIndiv set EtatChambre=? Where IdChambre=?");
            ps.setBoolean(1,false);
            ps.setInt(2,chambre.getId());
            if(ps.executeUpdate()>0) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO Inscription(DateInscription,DateDebut,Duree,IdChambre,IdStudent,current) values (?,?,?,?,?,true)", Statement.RETURN_GENERATED_KEYS);
                st.setDate(1, Date.valueOf(LocalDate.now()));
                st.setDate(2, Date.valueOf(LocalDate.now()));
                st.setInt(3, duree);
                st.setInt(4, chambre.getId());
                st.setInt(5, std.getId());
                return (st.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean reserverChambreA2(Student std, ChambreA2 chambre, int duree) {
        if (!chambre.isEtatPlace1() && !chambre.isEtatPlace2())
            return false;
        int freeplace = chambre.isEtatPlace1()? 1 : 2;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(true);
            String query = "UPDATE ChambreA2 set ";
            if(freeplace==1)
                query+="EtatPlace1=?";
            else
                query+="EtatPlace2=?";
            query+=" WHERE IdChambre=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1,false);
            ps.setInt(2,chambre.getId());
            if(ps.executeUpdate()>0) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO Inscription(DateInscription,DateDebut,Duree,IdChambre,IdStudent,current) values (?,?,?,?,?,true)", Statement.RETURN_GENERATED_KEYS);
                st.setDate(1, Date.valueOf(LocalDate.now()));
                st.setDate(2, Date.valueOf(LocalDate.now()));
                st.setInt(3, duree);
                st.setInt(4, chambre.getId());
                st.setInt(5, std.getId());
                return (st.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean reserverChambreA3(Student std, ChambreA3 chambre, int duree) {
        if (!chambre.isEtatPlace1() && !chambre.isEtatPlace2() && !chambre.isEtatPlace3())
            return false;
        int freeplace = chambre.isEtatPlace1()? 1 : chambre.isEtatPlace2()? 2:3;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(true);
            String query = "UPDATE ChambreA3 set ";
            if(freeplace==1)
                query+="EtatPlace1=?";
            else if(freeplace==2)
                query+="EtatPlace2=?";
            else
                query+="EtatPlace3=?";
            query+=" WHERE IdChambre=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1,false);
            ps.setInt(2,chambre.getId());
            if(ps.executeUpdate()>0) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO Inscription(DateInscription,DateDebut,Duree,IdChambre,IdStudent,current) values (?,?,?,?,?,true)", Statement.RETURN_GENERATED_KEYS);
                st.setDate(1, Date.valueOf(LocalDate.now()));
                st.setDate(2, Date.valueOf(LocalDate.now()));
                st.setInt(3, duree);
                st.setInt(4, chambre.getId());
                st.setInt(5, std.getId());
                return (st.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean reserverChambreA4(Student std, ChambreA4 chambre, int duree) {
        if (!chambre.isEtatPlace1() && !chambre.isEtatPlace2() && !chambre.isEtatPlace3() && !chambre.isEtatPlace4())
            return false;
        int freeplace = chambre.isEtatPlace1()? 1 : (chambre.isEtatPlace2()? 2: ( chambre.isEtatPlace4()? 3:4 ) ) ;
        Connection conn = null;
        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(true);
            String query = "UPDATE ChambreA3 set ";
            if(freeplace==1)
                query+="EtatPlace1=?";
            else if(freeplace==2)
                query+="EtatPlace2=?";
            else if(freeplace==3)
                query+="EtatPlace3=?";
            else
                query+="EtatPlace3=?";
            query+=" WHERE IdChambre=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1,false);
            ps.setInt(2,chambre.getId());
            if(ps.executeUpdate()>0) {
                PreparedStatement st = conn.prepareStatement("INSERT INTO Inscription(DateInscription,DateDebut,Duree,IdChambre,IdStudent,current) values (?,?,?,?,?,true)", Statement.RETURN_GENERATED_KEYS);
                st.setDate(1, Date.valueOf(LocalDate.now()));
                st.setDate(2, Date.valueOf(LocalDate.now()));
                st.setInt(3, duree);
                st.setInt(4, chambre.getId());
                st.setInt(5, std.getId());
                return (st.executeUpdate() > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<ChambreIndividuelle> getIndivFemaleReservedRooms() throws Exception {
        List<ChambreIndividuelle> chambres = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT * from chambre,chambreIndiv,Inscription WHERE Chambre.IdChambre = chambreIndiv.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreIndividuelle(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5)));
        }
        return chambres;
    }

    public static List<ChambreIndividuelle> getIndivMaleReservedRooms() throws Exception {
        List<ChambreIndividuelle> chambres = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT * from chambre,chambreIndiv,Inscription WHERE Chambre.IdChambre = chambreIndiv.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreIndividuelle(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5)));
        }
        return chambres;
    }

    public static List<ChambreIndividuelle> getIndivFemaleFreeRooms() throws Exception {
        List<ChambreIndividuelle> chambres = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT Chambre.Idchambre,CodeChambre,IdEtage,IdChambreIndiv,EtatPlace1,EtatPlace2,IdChambr from chambre,chambreIndiv WHERE Chambre.IdChambre = chambreIndiv.IdChambre and Chambre.IdChambre NOT IN(Select IdChambre FROM Inscription WHERE Inscription.current=true )");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreIndividuelle(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5)));
        }
        return chambres;
    }

    public static List<ChambreIndividuelle> getIndivMaleFreeRooms() throws Exception {
        List<ChambreIndividuelle> chambres = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT Chambre.Idchambre,CodeChambre,IdEtage,IdChambreIndiv,EtatChambre,Chambre.IdChambre from chambre,chambreIndiv WHERE Chambre.IdChambre = chambreIndiv.IdChambre and Chambre.IdChambre NOT IN(Select IdChambre FROM Inscription WHERE Inscription.current=true  )");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreIndividuelle(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5)));
        }
        return chambres;
    }

    public static List<ChambreA2> getA2FemaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA2> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA2,EtatPlace1,EtatPlace2 from chambre,chambreA2,Inscription WHERE Chambre.IdChambre = chambreA2.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA2(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6)));
        }

        return chambres;
    }

    public static List<ChambreA2> getA2MaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA2> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA2,EtatPlace1,EtatPlace2 from chambre,chambreA2,Inscription WHERE Chambre.IdChambre = chambreA2.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true  and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true )<2 and IdEtage IN(SELECT IdEtage FROM Etage" +
                " WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA2(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6)));
        }

        return chambres;
    }

    public static List<ChambreA2> getA2MaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA2> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA2,EtatPlace1,EtatPlace2 from chambre,chambreA2 WHERE Chambre.IdChambre = chambreA2.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true and IdChambre=Chambre.IdChambre)<2 and IdEtage IN(SELECT IdEtage FROM Etage" +
                " WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA2(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6)));
        }
        for(ChambreA2 temp : chambres)
        {
            System.out.println("Building " + temp.getEtage().getBatiment().getCode());
            System.out.println("floor " + temp.getEtage().getCodeEtage());
        }
        return chambres;
    }

    public static List<ChambreA2> getA2FemaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA2> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA2,EtatPlace1,EtatPlace2 from chambre,chambreA2 WHERE Chambre.IdChambre = chambreA2.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true and IdChambre=Chambre.IdChambre)<2 and IdEtage IN(SELECT IdEtage FROM Etage" +
                " WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA2(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6)));
        }
        for(ChambreA2 temp : chambres)
        {
            System.out.println("Building " + temp.getEtage().getBatiment().getCode());
            System.out.println("floor " + temp.getEtage().getCodeEtage());
        }
        return chambres;
    }

    public static List<ChambreA3> getA3FemaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA3> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA3,EtatPlace1,EtatPlace2,EtatPlace3 from chambre,chambreA3,Inscription WHERE Chambre.IdChambre = chambreA3.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA3(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7)));
        }

        return chambres;
    }

    public static List<ChambreA3> getA3MaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA3> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA3,EtatPlace1,EtatPlace2,EtatPlace3 from chambre,chambreA3,Inscription WHERE Chambre.IdChambre = chambreA3.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA3(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7)));
        }

        return chambres;
    }
    public static List<ChambreA3> getA3FemaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA3> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA3,EtatPlace1,EtatPlace2,EtatPlace3 from chambre,chambreA3 WHERE Chambre.IdChambre = chambreA3.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true and IdChambre=Chambre.IdChambre )<3 and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA3(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7)));
        }

        return chambres;
    }

    public static List<ChambreA3> getA3MaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA3> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECt  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA3,EtatPlace1,EtatPlace2,EtatPlace3 from chambre,chambreA3 WHERE Chambre.IdChambre = chambreA3.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true and IdChambre=Chambre.IdChambre )<3 and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA3(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7)));
        }

        return chambres;
    }


    public static List<ChambreA4> getA4FemaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA4> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA4,EtatPlace1,EtatPlace2,EtatPlace3,EtatPlace4 from chambre,chambreA4,Inscription WHERE Chambre.IdChambre = chambreA4.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }
        return chambres;
    }

    public static List<Data> getLatestReservations() throws Exception {
        List<Data> data = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT NameUser,CodeChambre FROM AppUser,Chambre,Inscription WHERE Chambre.IdChambre=Inscription.IdChambre and AppUser.IdUser=(Select IdUser FROM Student Where IdStudent=Inscription.IdStudent) Order By DateInscription Limit 10");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            data.add(new Data(rs.getString(1),rs.getString(2)));
        }
        return data;
    }

    public static List<ChambreA4> getA4MaleReservedRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA4> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT  Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA4,EtatPlace1,EtatPlace2,EtatPlace3,EtatPlace4 from chambre,chambreA4,Inscription WHERE Chambre.IdChambre = chambreA4.IdChambre and Inscription.IdChambre = chambre.IdChambre and Inscription.current=true and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }
        return chambres;
    }
    public static List<ChambreA4> getA4FemaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA4> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA4,EtatPlace1,EtatPlace2,EtatPlace3,EtatPlace4 from chambre,chambreA4 WHERE Chambre.IdChambre = chambreA4.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true )<4 and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='F'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }
        return chambres;
    }

    public static List<ChambreA4> getA4MaleFreeRooms() throws Exception {
        Connection conn = DBConnect.getConnection();
        List<ChambreA4> chambres = new ArrayList<>();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT Chambre.Idchambre,CodeChambre,IdEtage,IdChambreA4,EtatPlace1,EtatPlace2,EtatPlace3,EtatPlace4 from chambre,chambreA4 WHERE Chambre.IdChambre = chambreA4.IdChambre and (Select count(IdChambre) FROM Inscription WHERE Inscription.current=true and IdChambre=Chambre.IdChambre )<4 and IdEtage IN(SELECT IdEtage FROM Etage WHERE IdBatiment IN(SELECT IdBatiment FROM Batiment WHERE gender='M'))");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            chambres.add(new ChambreA4(rs.getInt(1),rs.getString(2),EtageDAO.getEtageById(rs.getInt(3)),rs.getInt(4),rs.getBoolean(5),rs.getBoolean(6),rs.getBoolean(7),rs.getBoolean(8)));
        }
        return chambres;
    }

    public static Date getStartDateMAxByRoom(int idroom) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("SELECT Max(DateDebut) FROM Inscription WHERE IdChambre=? and current=true ");
        ps.setInt(1,idroom);
        ResultSet rs = ps.executeQuery();
        return rs.getDate(1);
    }

    public static boolean freeRoom(int idchambnre) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement ps = conn.prepareStatement("UPDATE Inscription SET current=? WHERE IdChambre=?");
        ps.setBoolean(1,false);
        return ps.executeUpdate()>0;
    }

}
