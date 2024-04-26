package controller.ChuyenManHinh;

import java.awt.Color;
import javax.swing.*;

import controller.dashboard_controller.DanhMucBean;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import view.dashboard.TeacherJPanel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.dashboard.BoardingRoomJPanel;
import view.dashboard.ClassJPanel;
import view.dashboard.InfoJPanel;
import view.dashboard.MainJPanel;
import view.dashboard.PaymentJPanel;
import view.dashboard.PupilJPanel;

/**
 *
 * @author huant
 */
public class ChuyenManHinh {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinh(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "Main";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new FlowLayout());
        root.add(new MainJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Main":
                    node = new MainJPanel();
                    break;
                case "Teacher":
                {
                    try {
                        node = new TeacherJPanel();
                    } catch (SQLException ex) {
                        Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ChuyenManHinh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "Pupil":
                    node = new PupilJPanel();
                    break;
                case "Class":
                    node = new ClassJPanel();
                    break;
                case "BoardingRoom":
                    node = new BoardingRoomJPanel();
                    break;
                case "Payment":
                    node = new PaymentJPanel();
                    break;    
                case "Info":
                    node = new InfoJPanel();
                    break;
                    
                default:
                 //   node = new MainJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(0, 153, 255));
                jlbItem.setBackground(new Color(0, 153, 255));
            }
        }

    }

    private void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase("Main")) {
                item.getJpn().setBackground(new Color(255,255,255));
                item.getJlb().setBackground(new Color(255,255,255));
                continue;
            }
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            } else {
                item.getJpn().setBackground(new Color(0, 153, 255));
                item.getJlb().setBackground(new Color(0, 153, 255));
            }
        }
    }
}
