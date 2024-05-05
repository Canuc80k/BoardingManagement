import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class Test {

    public static DefaultTableModel loadFromFile(String fileName) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columns = {"ID", "Name", "Class", "Time"};
        model.setColumnIdentifiers(columns);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowData = line.split(",");
                model.addRow(new Object[]{
                    rowData[0], rowData[1], rowData[2], rowData[3]
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    public static void saveToFile(JTable table, String fileName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int row = 0; row < rowCount; row++) {
                StringBuilder rowString = new StringBuilder();
                for (int col = 0; col < colCount; col++) {
                    rowString.append(model.getValueAt(row, col));
                    if (col < colCount - 1) {
                        rowString.append(",");
                    }
                }
                writer.write(rowString.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Load data from file to JTable
        String loadFileName = "table_data.txt";
        DefaultTableModel loadedModel = loadFromFile(loadFileName);
        loadedModel.addRow(new Object[]{"Test2","Test2","Test2","Test2"});
        JTable table = new JTable(loadedModel);

        // Display JTable in a JFrame
        JFrame frame = new JFrame("Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1100, 400));

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
        saveToFile(table,loadFileName);
    }
}
