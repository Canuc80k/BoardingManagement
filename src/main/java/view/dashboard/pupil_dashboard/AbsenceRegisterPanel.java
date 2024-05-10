/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.dashboard.pupil_dashboard;

import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.dashboard_controller.pupil_dashboard_controller.AbsenceRegisterController;
import model.account.Account;

/**
 *
 * @author Lenovo
 */
public class AbsenceRegisterPanel extends javax.swing.JPanel {
    /**
     * Creates new form AbsenceRegister
     */
    Account account;
    private AbsenceRegisterController controller;
    private final int ROW = 6;
    private final int COL = 7;
    private JButton[][] calendarCell = new JButton[ROW + 1][COL + 1];

    public AbsenceRegisterPanel(Account account) throws ClassNotFoundException, SQLException {
        this.account = account;
        initComponents();
        customInit();
        controller = new AbsenceRegisterController(account, calendarCell, changeMonthYearDataLabel, monthChooserTextField, yearChooserTextField, currentMonthInformationLabel, boardingDayDescriptionPanel, absentDayDescriptionPanel, offDayDescriptionPanel);
        controller.loadCalendar();
        controller.setEvent();
    }

    void customInit() {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource("/images/reload_icon.png"));
            changeMonthYearDataLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {e.printStackTrace();}
        for (int i = 0; i <= ROW; i ++)
            for (int j = 1; j <= COL; j ++) {
                calendarCell[i][j] = new JButton();
                this.add(calendarCell[i][j]);
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currentMonthInformationLabel = new javax.swing.JLabel();
        chooseAnotherMonthLabel = new javax.swing.JLabel();
        monthChooserTextField = new javax.swing.JTextField();
        minusSignLabel = new javax.swing.JLabel();
        yearChooserTextField = new javax.swing.JTextField();
        changeMonthYearDataLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        boardingDayDescriptionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        absentDayDescriptionPanel = new javax.swing.JPanel();
        offDayDescriptionPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        currentMonthInformationLabel.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        currentMonthInformationLabel.setText("currentMonthInformationLabel");

        chooseAnotherMonthLabel.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        chooseAnotherMonthLabel.setText("Or you can choose another month/year");

        monthChooserTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        minusSignLabel.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        minusSignLabel.setText("/");

        yearChooserTextField.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        yearChooserTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearChooserTextFieldActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14))); // NOI18N

        boardingDayDescriptionPanel.setBackground(new java.awt.Color(51, 255, 51));
        boardingDayDescriptionPanel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout boardingDayDescriptionPanelLayout = new javax.swing.GroupLayout(boardingDayDescriptionPanel);
        boardingDayDescriptionPanel.setLayout(boardingDayDescriptionPanelLayout);
        boardingDayDescriptionPanelLayout.setHorizontalGroup(
            boardingDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        boardingDayDescriptionPanelLayout.setVerticalGroup(
            boardingDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Boarding Day");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Absent Day");

        absentDayDescriptionPanel.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout absentDayDescriptionPanelLayout = new javax.swing.GroupLayout(absentDayDescriptionPanel);
        absentDayDescriptionPanel.setLayout(absentDayDescriptionPanelLayout);
        absentDayDescriptionPanelLayout.setHorizontalGroup(
            absentDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        absentDayDescriptionPanelLayout.setVerticalGroup(
            absentDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        offDayDescriptionPanel.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout offDayDescriptionPanelLayout = new javax.swing.GroupLayout(offDayDescriptionPanel);
        offDayDescriptionPanel.setLayout(offDayDescriptionPanelLayout);
        offDayDescriptionPanelLayout.setHorizontalGroup(
            offDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        offDayDescriptionPanelLayout.setVerticalGroup(
            offDayDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Off Day");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(offDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boardingDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(absentDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(boardingDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(absentDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(offDayDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentMonthInformationLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chooseAnotherMonthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthChooserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(minusSignLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(yearChooserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(changeMonthYearDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(646, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(currentMonthInformationLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chooseAnotherMonthLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(monthChooserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(minusSignLabel)
                                .addComponent(yearChooserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(changeMonthYearDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        monthChooserTextField.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void yearChooserTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearChooserTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearChooserTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel absentDayDescriptionPanel;
    private javax.swing.JPanel boardingDayDescriptionPanel;
    private javax.swing.JLabel changeMonthYearDataLabel;
    private javax.swing.JLabel chooseAnotherMonthLabel;
    private javax.swing.JLabel currentMonthInformationLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minusSignLabel;
    private javax.swing.JTextField monthChooserTextField;
    private javax.swing.JPanel offDayDescriptionPanel;
    private javax.swing.JTextField yearChooserTextField;
    // End of variables declaration//GEN-END:variables
}
