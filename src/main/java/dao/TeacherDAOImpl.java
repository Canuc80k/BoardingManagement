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
                Teacher temp=new Teacher(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5));
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
        TeacherDAO temp=new TeacherDAOImpl();
        System.out.println(temp.getList());
    }

    @Override
    public int createOrUpdate(Teacher teacher) {
       try{
           Connection con=DBConnect.getConnection();
           String query="INSERT INTO teacher(id,name,dateofbirth,phonenumber,address,classid) values(?,?,?,?,?,?)";
           PreparedStatement ps=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
           ps.setString(1,teacher.getID());
           ps.setString(2,teacher.getName());
           ps.setDate(3,new Date(teacher.getDoB().getTime()));
           ps.setString(4,teacher.getPhone());
           ps.setString(5,teacher.getAddress());
           ps.setString(6,teacher.getClassID());
           ps.execute();
           ResultSet rs=ps.getGeneratedKeys();
           int generatedKey=0;
           if(rs.next()){
               generatedKey=rs.getInt(1);
           }
           ps.close();
           con.close();
           return generatedKey;
       }catch(Exception e){
           e.printStackTrace();
       }
        return 0;
    }
}
