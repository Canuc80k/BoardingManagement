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

    public static List<Pupil> getAllPupil(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<Pupil> res = new ArrayList<>();
        while (rs.next()) {
//            System.out.println("ID getting DB: " + rs.getString(1));
//            System.out.println("Gender getting DB: " +rs.getInt(4));
            Pupil temp = new Pupil(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
//            System.out.println("Name getting DB: " + temp.getName());
//            System.out.println("Gender: "+temp.getGender());
            res.add(temp);
        }
        rs.close();
        stmt.close();
        con.close();
        return res;
    }

    public static int countPupils() throws SQLException, ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) AS pupilCount FROM pupil";

        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", ""); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt("pupilCount");
            }
        }

        return count;
    }

    public static int create(Pupil pupil) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");

            String query = "INSERT INTO pupil(ID,name,dateofbirth,gender,class,parentname,phonenumber,address,boardingroom) "
                    + "VALUES (?, ?, ?, ?,?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, pupil.getID());
            pstmt.setString(2, pupil.getName());
            pstmt.setDate(3, new java.sql.Date(pupil.getDoB().getTime()));
            pstmt.setInt(4, pupil.getGender());
            pstmt.setString(5, pupil.getClassID());
            pstmt.setString(6, pupil.getParentName());
            pstmt.setString(7, pupil.getPhone());
            pstmt.setString(8, pupil.getAddress());
            pstmt.setString(9, pupil.getBoardingroom());
            // pstmt.setInt(10, pupil.getAbsentday());
            // pstmt.setInt(10, pupil.getGender());

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

            String query = "UPDATE pupil SET name=?, dateofbirth=?, gender=?,class=?,parentName=? ,phonenumber=?, address=?,boardingroom=? WHERE ID=?";

            PreparedStatement pstmt = con.prepareStatement(query);

//            System.out.println("Updating pupil Name: " + pupil.getName());
//            System.out.println("Date of Birth: " + pupil.getDoB());
//            System.out.println("Gender: " + pupil.getGender());
//            System.out.println("Class ID: " + pupil.getClassID());
//            System.out.println("Parent Name: " + pupil.getParentName());
//            System.out.println("Phone: " + pupil.getPhone());
//            System.out.println("Address: " + pupil.getAddress());
//            System.out.println("Boarding Room: " + pupil.getBoardingroom());
//            System.out.println("ID: " + pupil.getID());
            pstmt.setString(1, pupil.getName());
            pstmt.setDate(2, new java.sql.Date(pupil.getDoB().getTime()));
            pstmt.setInt(3, pupil.getGender());
            pstmt.setString(4, pupil.getClassID());
            pstmt.setString(5, pupil.getParentName());
            pstmt.setString(6, pupil.getPhone());
            pstmt.setString(7, pupil.getAddress());
            pstmt.setString(8, pupil.getBoardingroom());
            // pstmt.setInt(9, pupil.getAbsentday());
            //pstmt.setBoolean(9, pupil.getGender());
            pstmt.setString(9, pupil.getID());

            int rowsUpdated = pstmt.executeUpdate(); // Execute the update query

            pstmt.close();
            con.close();

            return rowsUpdated; // Return the number of rows updated
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
//    public static int update(Pupil pupil) {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardingmanagement", "root", "");
//
//            String query = "UPDATE pupil SET name=?, dateofbirth=?, phonenumber=?, address=?, parentName=?, absentday=? WHERE ID=?";
//
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setString(1, pupil.getName());
//            pstmt.setDate(2, new java.sql.Date(pupil.getDoB().getTime()));
//            pstmt.setString(3, pupil.getPhone());
//            pstmt.setString(4, pupil.getAddress());
//            pstmt.setString(5, pupil.getParentName());
//            pstmt.setInt(6, pupil.getAbsentday());
//            pstmt.setString(7, pupil.getID());
//
//            int rowsUpdated = pstmt.executeUpdate(); // Execute the update query
//
//            pstmt.close();
//            con.close();
//
//            return rowsUpdated; // Return the number of rows updated
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

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
