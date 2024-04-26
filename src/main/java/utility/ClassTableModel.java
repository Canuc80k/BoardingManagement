package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.people.teacher.Teacher;


public class ClassTableModel {
       public DefaultTableModel setTableTeacher(List<Teacher> listItem,String[] listColumn){
           DefaultTableModel model=new DefaultTableModel(){
               @Override
               public boolean isCellEditable(int row, int column) {
                   return false;               }
               
           };
           model.setColumnIdentifiers(listColumn);
           int columns=listColumn.length;
           Object[] obj=null;
           int rows=listItem.size();
           if(rows>0){
               for(int i=0;i<rows;i++){
                   Teacher temp=listItem.get(i);
                   obj=new Object[columns];
                   obj[0]=temp.getID();
                   obj[1]=temp.getName();
                   obj[2]=temp.getDoB();
                   obj[3]=temp.getPhone();
                   obj[4]=temp.getAddress();
                   obj[5]=temp.getClassID();
                  
                   model.addRow(obj);
               }
           }
           return model;
       }
}
