

package model.people.pupil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PupilDatabase {

    public PupilDatabase() {
    }

    public static List<Pupil> getAllPupils(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Pupil> res = new ArrayList<>();
        while (rs.next()) {
            Pupil temp = new Pupil(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getBoolean(10));

            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int create(Pupil pupil) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO pupil(ID, name, dateofbirth, phonenumber, address, classID, parentName, boardingroom, absentday, gender) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, pupil.getID());
            pstmt.setString(2, pupil.getName());
            pstmt.setDate(3, new java.sql.Date(pupil.getDoB().getTime()));
            pstmt.setString(4, pupil.getPhone());
            pstmt.setString(5, pupil.getAddress());
            pstmt.setString(6, pupil.getClassID());
            pstmt.setString(7, pupil.getParentName());
            pstmt.setString(8, pupil.getBoardingroom());
            pstmt.setInt(9, pupil.getAbsentday());
            pstmt.setBoolean(10, pupil.getGender());

            int rowsInserted = pstmt.executeUpdate();

            pstmt.close();
            con.close();
            return rowsInserted; // Return number of rows inserted
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int update(Pupil pupil) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "UPDATE pupil SET name=?, dateofbirth=?, phonenumber=?, address=?, classID=?, parentName=?, boardingroom=?, absentday=?, gender=? WHERE ID=?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, pupil.getName());
            pstmt.setDate(2, new java.sql.Date(pupil.getDoB().getTime()));
            pstmt.setString(3, pupil.getPhone());
            pstmt.setString(4, pupil.getAddress());
            pstmt.setString(5, pupil.getClassID());
            pstmt.setString(6, pupil.getParentName());
            pstmt.setString(7, pupil.getBoardingroom());
            pstmt.setInt(8, pupil.getAbsentday());
            pstmt.setBoolean(9, pupil.getGender());
            pstmt.setString(10, pupil.getID());

            int rowsUpdated = pstmt.executeUpdate(); // Execute the update query

            pstmt.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(Pupil pupil) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
            Statement stmt = con.createStatement();

            String query = "DELETE FROM pupil WHERE ID='" + pupil.getID() + "'";

            int rowsDeleted = stmt.executeUpdate(query); // Execute the delete query

            stmt.close();
            con.close();

            return rowsDeleted; // Return the number of rows deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}