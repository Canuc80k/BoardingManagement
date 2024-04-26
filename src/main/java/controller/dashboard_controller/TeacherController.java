package controller.dashboard_controller;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.people.teacher.Teacher;
import service.TeacherService;
import service.TeacherServiceImpl;
public class TeacherController {
    private JButton btnSave;
    private JTextField jtfTeacherID;
    private JTextField jtfName;
    private JDateChooser jdcNgaySinh;
    private JTextField jtfPhone;
    private JTextField jtfAddress;
    private JTextField jtfClassID;
    private Teacher teacher=null;
    private JLabel jlbMsg;
    private TeacherService teacherService=null;
    public TeacherController(JButton btnSave, JTextField jtfTeacherID, JTextField jtfName, 
            JDateChooser jdcNgaySinh, JTextField jtfPhone, JTextField jtfAddress, JTextField jtfClassID,Teacher teacher,JLabel jlbMsg) {
        this.btnSave = btnSave;
        this.jtfTeacherID = jtfTeacherID;
        this.jtfName = jtfName;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jtfPhone = jtfPhone;
        this.jtfAddress = jtfAddress;
        this.jtfClassID = jtfClassID;
        this.jlbMsg=jlbMsg;
        this.teacherService=new TeacherServiceImpl();
    }

    public void setView(Teacher teacher){
        this.teacher=teacher;
        jtfTeacherID.setText(teacher.getID());
        jtfName.setText(teacher.getName());
        jtfPhone.setText(teacher.getPhone());
        jtfAddress.setText(teacher.getAddress());
        jdcNgaySinh.setDate(teacher.getDoB());
        jtfClassID.setText(teacher.getClassID());
    }
    public void setEvent(){
        btnSave.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfName.getText().length()==0||jdcNgaySinh.getDate()==null){
                    jlbMsg.setText("Please enter fill compulsory informations");
                }else {
                    teacher=new Teacher(jtfTeacherID.getText(),jtfName.getText(),jdcNgaySinh.getDate(),jtfPhone.getText(),jtfAddress.getText());
                    teacher.setClassID(jtfClassID.getText());
                    int lastEnteredID = teacherService.createOrUpdate(teacher);
                    if(lastEnteredID > 0){
                        
                        jlbMsg.setText("Update Success");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               btnSave.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSave.setBackground(new Color(100,221,23));
            }
            
        });
    }
}
