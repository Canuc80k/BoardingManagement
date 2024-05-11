package controller.dashboard_controller.pupil_dashboard_controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.absence.AbsenceDatabase;
import model.account.Account;
import view.dashboard.pupil_dashboard.AbsenceHistory;

public class AbsenceHistoryController {
    private Account account;
    private JTable absenceHistoryTable;
    private AbsenceHistory absenceHistory;

    public AbsenceHistoryController(AbsenceHistory absenceHistory, Account account, JTable absenceHistoryTable) throws ClassNotFoundException, SQLException {
        this.absenceHistory = absenceHistory;
        this.account = account;
        this.absenceHistoryTable = absenceHistoryTable;
        setDataForAbsenceHistoryTable();
    }

    private void setDataForAbsenceHistoryTable() throws ClassNotFoundException, SQLException {
        List<String> absenceHistoryList = AbsenceDatabase.getAbsenceHistory(account.getID());
        Collections.reverse(absenceHistoryList);
        DefaultTableModel model = new DefaultTableModel(new Object [][] {}, new String [] {"Day", "State",}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < absenceHistoryList.size(); i ++) {
            System.out.println(absenceHistoryList.get(i));
            model.addRow(new Object[] {
                absenceHistoryList.get(i), getState(absenceHistoryList.get(i))
            });
        }
        absenceHistoryTable.setModel(model);
        this.absenceHistory.validate();
        this.absenceHistory.repaint();
    }

    private String getState(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        if (LocalDate.now().isBefore(date)) return "Register Successfully";
        return "Absented";
    }
}
