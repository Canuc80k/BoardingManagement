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
public class ManageTeacherController {
    private JButton saveButton;
    private JTextField teacherIDTextField;
    private JTextField nameTextField;
    private JDateChooser dobDayChooser;
    private JTextField phoneTextField;
    private JTextField addressTextField;
    private JTextField classIDTextField;
    private Teacher teacher=null;
    private JLabel messageLabel;
    private TeacherService teacherService=null;
    public ManageTeacherController(JButton btnSave, JTextField jtfTeacherID, JTextField jtfName, 
            JDateChooser jdcNgaySinh, JTextField jtfPhone, JTextField jtfAddress, JTextField jtfClassID,Teacher teacher,JLabel jlbMsg) {
        this.saveButton = btnSave;
        this.teacherIDTextField = jtfTeacherID;
        this.nameTextField = jtfName;
        this.dobDayChooser = jdcNgaySinh;
        this.phoneTextField = jtfPhone;
        this.addressTextField = jtfAddress;
        this.classIDTextField = jtfClassID;
        this.messageLabel=jlbMsg;
        this.teacherService=new TeacherServiceImpl();
    }

    public void setView(Teacher teacher){
        this.teacher=teacher;
        teacherIDTextField.setText(teacher.getID());
        nameTextField.setText(teacher.getName());
        phoneTextField.setText(teacher.getPhone());
        addressTextField.setText(teacher.getAddress());
        dobDayChooser.setDate(teacher.getDoB());
        
        classIDTextField.setText(teacher.getClassID());//System.out.println(teacher.getAddress()+" "+teacher.getClassID());
    }
    public void setEvent(String editOrAdd){
        saveButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(nameTextField.getText().length()==0||dobDayChooser.getDate()==null){
                    messageLabel.setText("Please enter fill compulsory informations");
                }else {
                    teacher=new Teacher(teacherIDTextField.getText(),nameTextField.getText(),dobDayChooser.getDate(),phoneTextField.getText(),addressTextField.getText());
                    teacher.setClassID(classIDTextField.getText());
                    int check = -1 ; 
                    if(editOrAdd=="Add") check=teacherService.create(teacher);
                    else check=teacherService.update(teacher);
                    if(check >0){
                        
                        messageLabel.setText("Update Success");
                    } else {
                        messageLabel.setText("Update Fail");
                    }
                  //  controller.
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               saveButton.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(100,221,23));
            }
            
        });
    }
}
