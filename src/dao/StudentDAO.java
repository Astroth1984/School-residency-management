package dao;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    /**
     *
     * @return List<Student>
     * @throws Exception
     */
    public static List<Student> getAllStudent() throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        ArrayList<Student> students = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser");
        while(rs.next())
        {
            students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
        }
        return students;
    }

    /**
     *
     * @param cin
     * @return
     * @throws Exception
     */
    public static Student getStudentByCIN(String cin) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and AppUser.cinUser=?");
        st.setString(1,cin);
        return getStudent(st);
    }

    /**
     *
     * @param iduser
     * @return
     * @throws Exception
     */
     public static Student getStudentByIdUser(int iduser) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and AppUser.IdUSer=?");
        st.setInt(1,iduser);
        return getStudent(st);
    }

    /**
     *
     * @param IdStudent
     * @return
     * @throws Exception
     */
    public static Student getStudentByIdStudent(int IdStudent) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and AppUser.IdStudent=?");
        st.setInt(1,IdStudent);
        return getStudent(st);
    }

    /**
     *
     * @param cne
     * @return
     * @throws Exception
     */
    public static Student getStudentByCNE(String cne) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and Student.CNE=?");
        st.setString(1,cne);
        return getStudent(st);
    }

    /**
     *
     * @param email
     * @return
     * @throws Exception
     */
    public static Student getStudentByEmail(String email) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and AppUser.EmailUser=?");
        st.setString(1,email);
        return getStudent(st);
    }

    /**
     *
     * @param cne
     * @param password
     * @return
     * @throws Exception
     */
    public static Student studentLogin(String cne,String password) throws Exception
    {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("SELECT AppUser.IdUser, NameUser,EmailUser,PhoneUser,CINUser,IdStudent,Level,CNE FROM AppUser,Student WHERE AppUser.IdUser = Student.IdUser and (Student.CNE=? or appUser.emailuser=?) and AppUser.passwordUser=?");
        st.setString(1,cne);
        st.setString(2,cne);
        st.setString(3,password);
        return getStudent(st);
    }

    /**
     * @desc Used to eliminate duplicated code
     * @param st
     * @return Student
     * @throws SQLException
     */
    private static Student getStudent(PreparedStatement st) throws SQLException {
        Student student = null;
        ResultSet rs = st.executeQuery();
        if(rs.next())
        {
            student = new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8));
        }
        return student;
    }

    /**
     *
     * @param nameUser
     * @param EmailUser
     * @param PhoneUser
     * @param CINUser
     * @param Level
     * @param CNE
     * @param passwordUser
     * @return
     */
    public static boolean addNewStudent(String nameUser,String EmailUser,String PhoneUser, String CINUser, String Level, String CNE, String passwordUser)
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
                    PreparedStatement st2 = conn.prepareStatement("INSERT INTO Student(level,CNE,idUser) values (?,?,?)");
                    st2.setString(1,Level);
                    st2.setString(2,CNE);
                    st2.setInt(3,rs.getInt(1));
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

    /**
     *
     * @param cin
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentCIN(String cin,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET CIN=? WHERE IdUser=?");
        return executeUpdate(st,cin,IdUser);
    }

    /**
     *
     * @param phone
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentPhone(String phone,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET phoneUser=? WHERE IdUser=?");
        return executeUpdate(st,phone,IdUser);
    }

    /**
     *
     * @param mail
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentMail(String mail,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET emailUser=? WHERE IdUser=?");
        return executeUpdate(st,mail,IdUser);
    }

    /**
     *
     * @param name
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentName(String name,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET nameUser=? WHERE IdUser=?");
        return executeUpdate(st,name,IdUser);
    }

    /**
     *
     * @param password
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentPassword(String password,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE AppUser SET passwordUSer=? WHERE IdUser=?");
        return executeUpdate(st,password,IdUser);
    }

    /**
     *
     * @param CNE
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentCNE(String CNE,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE Student SET CNE=? WHERE IdUser=?");
        return executeUpdate(st,CNE,IdUser);
    }

    /**
     *
     * @param Level
     * @param IdUser
     * @return
     * @throws Exception
     */
    public static boolean updateStudentLevel(String Level,int IdUser) throws Exception {
        Connection conn = DBConnect.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement st = conn.prepareStatement("UPDATE Student SET Level=? WHERE IdUser=?");
        return executeUpdate(st,Level,IdUser);
    }

    /**
     *
     * @param st
     * @param param1
     * @param param2
     * @return
     * @throws Exception
     */
    private static boolean executeUpdate(PreparedStatement st,String param1,int param2) throws Exception
    {
        st.setString(1,param1);
        st.setInt(2,param2);

        return st.executeUpdate()>0;
    }
}
