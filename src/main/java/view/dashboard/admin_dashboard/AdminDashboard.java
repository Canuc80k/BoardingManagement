package view.dashboard.admin_dashboard;

import java.util.ArrayList;
import java.util.List;

import controller.dashboard_controller.SideFeatureOption;
import controller.dashboard_controller.admin_dashboard_controller.AdminDashboardController;
import model.account.Account;
import view.login.Login;

public class AdminDashboard extends javax.swing.JFrame {

    public AdminDashboard(Account account) {
        initComponents();
        setTitle("Boarding Management");
        
        AdminDashboardController controller = new AdminDashboardController(viewPanel, mainPanel, mainLabel,account);
        List<SideFeatureOption> listItem = new ArrayList<>();
        listItem.add(new SideFeatureOption("Main", mainPanel, mainLabel));
        listItem.add(new SideFeatureOption("Teacher", teacherPanel, teacherLabel));
        listItem.add(new SideFeatureOption("Manager", managerPanel, managerLabel));
        listItem.add(new SideFeatureOption("Pupil", pupilPanel, pupilLabel));
        listItem.add(new SideFeatureOption("Class", classPanel, classLabel));
        listItem.add(new SideFeatureOption("BoardingRoom", boardingRoomPanel, boardingRoomLabel));
        listItem.add(new SideFeatureOption("Payment", paymentPanel, paymentLabel));
        listItem.add(new SideFeatureOption("Info", informationPanel, informationLabel));
        controller.setEvent(listItem);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        viewPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        mainLabel = new javax.swing.JLabel();
        jlbTeacher1 = new javax.swing.JLabel();
        teacherPanel = new javax.swing.JPanel();
        teacherLabel = new javax.swing.JLabel();
        boardingRoomPanel = new javax.swing.JPanel();
        boardingRoomLabel = new javax.swing.JLabel();
        classPanel = new javax.swing.JPanel();
        classLabel = new javax.swing.JLabel();
        pupilPanel = new javax.swing.JPanel();
        pupilLabel = new javax.swing.JLabel();
        paymentPanel = new javax.swing.JPanel();
        paymentLabel = new javax.swing.JLabel();
        informationPanel = new javax.swing.JPanel();
        informationLabel = new javax.swing.JLabel();
        logOutLabel = new javax.swing.JLabel();
        managerPanel = new javax.swing.JPanel();
        managerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1500, 765));

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1210, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuPanel.setBackground(new java.awt.Color(74, 188, 253));
        menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        mainLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        mainLabel.setText("Boarding Management");

        jlbTeacher1.setBackground(new java.awt.Color(238, 238, 228));
        jlbTeacher1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jlbTeacher1.setForeground(new java.awt.Color(153, 153, 153));
        jlbTeacher1.setText("Hi, Admin");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbTeacher1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbTeacher1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        teacherPanel.setBackground(new java.awt.Color(0, 153, 255));

        teacherLabel.setBackground(new java.awt.Color(238, 238, 228));
        teacherLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        teacherLabel.setForeground(new java.awt.Color(238, 238, 228));
        teacherLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher (1).png"))); // NOI18N
        teacherLabel.setText("Manage Teacher");

        javax.swing.GroupLayout teacherPanelLayout = new javax.swing.GroupLayout(teacherPanel);
        teacherPanel.setLayout(teacherPanelLayout);
        teacherPanelLayout.setHorizontalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(teacherPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(teacherLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        teacherPanelLayout.setVerticalGroup(
            teacherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, teacherPanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(teacherLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        boardingRoomPanel.setBackground(new java.awt.Color(0, 153, 255));

        boardingRoomLabel.setBackground(new java.awt.Color(238, 238, 228));
        boardingRoomLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        boardingRoomLabel.setForeground(new java.awt.Color(238, 238, 228));
        boardingRoomLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/restaurant.png"))); // NOI18N
        boardingRoomLabel.setText("Manage Boarding Room");

        javax.swing.GroupLayout boardingRoomPanelLayout = new javax.swing.GroupLayout(boardingRoomPanel);
        boardingRoomPanel.setLayout(boardingRoomPanelLayout);
        boardingRoomPanelLayout.setHorizontalGroup(
            boardingRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardingRoomPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(boardingRoomLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        boardingRoomPanelLayout.setVerticalGroup(
            boardingRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(boardingRoomPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(boardingRoomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        classPanel.setBackground(new java.awt.Color(0, 153, 255));

        classLabel.setBackground(new java.awt.Color(238, 238, 228));
        classLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        classLabel.setForeground(new java.awt.Color(238, 238, 228));
        classLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/classroom-32.png"))); // NOI18N
        classLabel.setText("Manage Class");

        javax.swing.GroupLayout classPanelLayout = new javax.swing.GroupLayout(classPanel);
        classPanel.setLayout(classPanelLayout);
        classPanelLayout.setHorizontalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(classLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        classPanelLayout.setVerticalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(classLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pupilPanel.setBackground(new java.awt.Color(0, 153, 255));

        pupilLabel.setBackground(new java.awt.Color(238, 238, 228));
        pupilLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        pupilLabel.setForeground(new java.awt.Color(238, 238, 228));
        pupilLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/student_little_boy.png"))); // NOI18N
        pupilLabel.setText("Manage Pupil");

        javax.swing.GroupLayout pupilPanelLayout = new javax.swing.GroupLayout(pupilPanel);
        pupilPanel.setLayout(pupilPanelLayout);
        pupilPanelLayout.setHorizontalGroup(
            pupilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pupilPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pupilLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pupilPanelLayout.setVerticalGroup(
            pupilPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pupilPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pupilLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        paymentPanel.setBackground(new java.awt.Color(0, 153, 255));

        paymentLabel.setBackground(new java.awt.Color(238, 238, 228));
        paymentLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        paymentLabel.setForeground(new java.awt.Color(238, 238, 228));
        paymentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/wallet.png"))); // NOI18N
        paymentLabel.setText("Manage Payment");

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(paymentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(paymentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        informationPanel.setBackground(new java.awt.Color(0, 153, 255));

        informationLabel.setBackground(new java.awt.Color(238, 238, 228));
        informationLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        informationLabel.setForeground(new java.awt.Color(238, 238, 228));
        informationLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/information.png"))); // NOI18N
        informationLabel.setText("Change Information");

        javax.swing.GroupLayout informationPanelLayout = new javax.swing.GroupLayout(informationPanel);
        informationPanel.setLayout(informationPanelLayout);
        informationPanelLayout.setHorizontalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(informationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        informationPanelLayout.setVerticalGroup(
            informationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informationPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(informationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        logOutLabel.setBackground(new java.awt.Color(238, 238, 228));
        logOutLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        logOutLabel.setForeground(new java.awt.Color(238, 238, 228));
        logOutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        logOutLabel.setText("Log Out");
        logOutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutLabelMouseClicked(evt);
            }
        });

        managerPanel.setBackground(new java.awt.Color(0, 153, 255));

        managerLabel.setBackground(new java.awt.Color(238, 238, 228));
        managerLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        managerLabel.setForeground(new java.awt.Color(238, 238, 228));
        managerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/teacher (1).png"))); // NOI18N
        managerLabel.setText("Manage Boarding Manager");

        javax.swing.GroupLayout managerPanelLayout = new javax.swing.GroupLayout(managerPanel);
        managerPanel.setLayout(managerPanelLayout);
        managerPanelLayout.setHorizontalGroup(
            managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(managerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        managerPanelLayout.setVerticalGroup(
            managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(managerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(informationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paymentPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boardingRoomPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(classPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pupilPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(logOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(teacherPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(managerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teacherPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(managerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pupilPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(classPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boardingRoomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(paymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(informationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(logOutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(viewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logOutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutLabelMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logOutLabelMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boardingRoomLabel;
    private javax.swing.JPanel boardingRoomPanel;
    private javax.swing.JLabel classLabel;
    private javax.swing.JPanel classPanel;
    private javax.swing.JLabel informationLabel;
    private javax.swing.JPanel informationPanel;
    private javax.swing.JLabel jlbTeacher1;
    private javax.swing.JLabel logOutLabel;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel managerLabel;
    private javax.swing.JPanel managerPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel paymentLabel;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JLabel pupilLabel;
    private javax.swing.JPanel pupilPanel;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JLabel teacherLabel;
    private javax.swing.JPanel teacherPanel;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}
