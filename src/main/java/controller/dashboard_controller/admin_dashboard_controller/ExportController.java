package controller.dashboard_controller.admin_dashboard_controller;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import model.classroom.Classroom;

public class ExportController {

    public ExportController(JTable table, Classroom classroom) throws FileNotFoundException, IOException {
        LocalDate ld = LocalDate.now();
        String sanitizedClassID = classroom.getClassID().replace("/", "-");
        String pdfName = sanitizedClassID +"_"+ ld + ".pdf";
        //String pdfName = @"" + classroom.getClassID().toString() + ld + ".pdf";
        if (Files.exists(Paths.get(pdfName))) {
            // If it exists, delete it
            Files.delete(Paths.get(pdfName));
        }
        PdfWriter pdfWriter = new PdfWriter(pdfName);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        float col = 280f;
        float columnWidth[] = {col, col};
        Table headerTable = new Table(columnWidth);

        // Set background color and font color for the table
        headerTable.setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(DeviceRgb.WHITE);

        // Add invoice title cell
        headerTable.addCell(new Cell().add("INVOICE")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(null)); // Remove border

        // Add school name cell
        headerTable.addCell(new Cell().add("DUT Primary School\nITF\n22T_KHDL")
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setMarginRight(10f)
                .setBorder(null)); // Remove border

        // person who made this export file's information
        float colWidth[] = {80, 300, 100, 80};
        Table peopleInfoTable = new Table(colWidth);
        peopleInfoTable.addCell(new Cell(0, 4).add("People information")
                .setBold().setBorder(null));
        peopleInfoTable.addCell(new Cell().add("Name").setBorder(null));
        peopleInfoTable.addCell(new Cell().add("Quan").setBorder(null)); //
        peopleInfoTable.addCell(new Cell().add("Invoice no.").setBorder(null));
        peopleInfoTable.addCell(new Cell().add("123").setBorder(null)); //
        peopleInfoTable.addCell(new Cell().add("Date").setBorder(null));
        peopleInfoTable.addCell(new Cell().add(ld.toString()).setBorder(null));
        peopleInfoTable.addCell(new Cell().add("Role").setBorder(null));
        peopleInfoTable.addCell(new Cell().add("Admin").setBorder(null));

        // Create a sample JTable (Replace this with your actual JTable)
        // JTable jTable = createSampleJTable();
        // Create the item info table from JTable data
        Table itemInfoTable = createItemInfoTableFromJTable(table);

        // Add spacing between tables
        document.add(new Paragraph("\n").setMarginBottom(20));
        document.add(headerTable);
        document.add(new Paragraph("\n").setMarginBottom(40));
        document.add(peopleInfoTable);
        document.add(new Paragraph("\n").setMarginBottom(40));
        document.add(itemInfoTable);
        document.close();
        System.out.println("Created");
    }

    // Method to create a sample JTable (Replace this with your actual JTable)
    private static JTable createSampleJTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{
                    {"John Doe", "Class A", "Room 101", "2024-05-17", 2, "Present"},
                    {"Jane Smith", "Class B", "Room 102", "2024-05-17", 0, "Absent"}
                // Add more rows as needed
                },
                new String[]{"Name", "Class", "Boarding Room", "Date", "Absence Days", "Status"}
        );
        return new JTable(model);
    }

    // Method to create the item info table from JTable data
    private static Table createItemInfoTableFromJTable(JTable jTable) {
        float itemInfoColWidth[] = {100, 100, 100, 100, 100, 100};
        Table itemInfoTable = new Table(itemInfoColWidth);
        //{"Name", "Class", "Boarding Room", "Date", "Absence Days", "Status"};

        // Add column headers
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            itemInfoTable.addCell(new Cell()
                    .add(jTable.getColumnName(i))
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
            );
        }

        // Add data rows
        for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int col = 0; col < jTable.getColumnCount(); col++) {
                itemInfoTable.addCell(new Cell().add(jTable.getValueAt(row, col).toString()));
            }
        }

        return itemInfoTable;
    }
}
