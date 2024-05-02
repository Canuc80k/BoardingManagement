package dao;

import java.util.List;
import model.people.teacher.Teacher;
import java.sql.*;
import java.util.ArrayList;

public class TeacherDAOImpl implements TeacherDAO {

    @Override
    public List<Teacher> getList() {
        try {
            Connection con = DBConnect.getConnection();
            String sql = "Select * from Teacher";
            List<Teacher> list = new ArrayList<>();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher temp = new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                temp.setClassID(rs.getString(6));
                list.add(temp);
            }
            ps.close();
            rs.close();
            con.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        TeacherDAO temp = new TeacherDAOImpl();
        System.out.println(temp.getList());
    }

    @Override
    public int createOrUpdate(Teacher teacher) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "INSERT INTO teacher(name,dateofbirth,phonenumber,address) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            // ps.setString(1,teacher.getID());
            ps.setString(1, teacher.getName());
            ps.setDate(2, new Date(teacher.getDoB().getTime()));
            ps.setString(3, teacher.getPhone());
            ps.setString(4, teacher.getAddress());
            // ps.setString(6,teacher.getClassID());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            con.close();
            return generatedKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // @Override
    // @Override
    public int create(Teacher teacher) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "INSERT INTO teacher(ID,name,dateofbirth,phonenumber,address,classID) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            // Set values for parameters
            ps.setString(1, teacher.getID()); // Assuming teacher.getID() returns the new teacher's ID
            ps.setString(2, teacher.getName()); // Assuming teacher.getClassID() returns the class ID
            ps.setDate(3, new Date(teacher.getDoB().getTime()));

            ps.setString(4, teacher.getPhone());
            ps.setString(5, teacher.getAddress());
            ps.setString(6, teacher.getClassID());

            int rowsInserted = ps.executeUpdate();

            ps.close();
            con.close();

            return rowsInserted; // Return number of rows inserted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // @Override
    public int update(Teacher teacher) {
        try {
            Connection con = DBConnect.getConnection();
            String query = "UPDATE teacher SET name=?, dateofbirth=?, phonenumber=?, address=? WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, teacher.getName());
            ps.setDate(2, new Date(teacher.getDoB().getTime()));
            ps.setString(3, teacher.getPhone());
            ps.setString(4, teacher.getAddress());
            //  ps.setString(, teacher.getClassID());
            ps.setString(5, teacher.getID());
            ps.executeUpdate();
            int rowsInserted = ps.executeUpdate();
            ps.close();
            con.close();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
