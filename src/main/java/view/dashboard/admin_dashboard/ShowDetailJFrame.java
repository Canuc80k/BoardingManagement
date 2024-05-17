package view.dashboard.admin_dashboard;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.dashboard_controller.admin_dashboard_controller.ShowDetailController;
import model.classroom.Classroom;

/**
 *
 * @author huant
 */
public class ShowDetailJFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShowJFrame
     */
    public ShowDetailJFrame(Classroom classroom) {
        initComponents();
        try {
            ShowDetailController controller;
            controller = new ShowDetailController(viewPanel, searchTextField, teacherLabel, classroomLabel,exportReportButton, classroom);
            controller.setDataToTable();
             controller.setEvent();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ShowDetailJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        viewPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        teacherLabel = new javax.swing.JLabel();
        classroomLabel = new javax.swing.JLabel();
        exportReportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        viewPanel.setEnabled(false);

        javax.swing.GroupLayout viewPanelLayout = new javax.swing.GroupLayout(viewPanel);
        viewPanel.setLayout(viewPanelLayout);
        viewPanelLayout.setHorizontalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1056, Short.MAX_VALUE)
        );
        viewPanelLayout.setVerticalGroup(
            viewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setText("Responsible:");

        teacherLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        teacherLabel.setText("Responsible:");

        classroomLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 55)); // NOI18N
        classroomLabel.setForeground(new java.awt.Color(255, 0, 51));
        classroomLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        classroomLabel.setText("Responsible:");

        exportReportButton.setBackground(new java.awt.Color(255, 0, 51));
        exportReportButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        exportReportButton.setForeground(new java.awt.Color(255, 255, 255));
        exportReportButton.setText("Export Report");
        exportReportButton.setBorder(null);
        exportReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(classroomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnRootLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnRootLayout.createSequentialGroup()
                                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(teacherLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(exportReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(classroomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(teacherLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(viewPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void exportReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportReportButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_exportReportButtonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel classroomLabel;
    private javax.swing.JButton exportReportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel teacherLabel;
    private javax.swing.JPanel viewPanel;
    // End of variables declaration//GEN-END:variables
}
