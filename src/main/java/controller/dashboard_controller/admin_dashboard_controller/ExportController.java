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
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import constant.TextUtils;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import model.account.Account;
import model.classroom.Classroom;
import model.people.People;
import model.people.admin.AdminDatabase;
import model.people.teacher.Teacher;
import model.people.teacher.TeacherDatabase;

public class ExportController {

    public ExportController(JTable table, Classroom classroom,Account account) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        LocalDate ld = LocalDate.now();
        String sanitizedClassID = classroom.getClassID().replace("/", "-");
        String pdfName = sanitizedClassID + "_" + ld + ".pdf";
        if (Files.exists(Paths.get(pdfName))) {
            Files.delete(Paths.get(pdfName));
        }
        PdfWriter pdfWriter = new PdfWriter(pdfName);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        People people;
        if(account.getRole()==1){
            people=AdminDatabase.getAdmin(account.getID());
        } else {
            people=TeacherDatabase.getTeacher(account.getID());
        }
       
        // Load custom font
        String fontPath = "vuArial.ttf"; // Path to the font file in your project folder
        PdfFont customFont = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);

        float col = 280f;
        float columnWidth[] = {col, col};
        Table headerTable = new Table(columnWidth);

        headerTable.setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(DeviceRgb.WHITE);

        headerTable.addCell(new Cell().add(new Paragraph("INVOICE")
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(null)
                .setFont(customFont)));

        headerTable.addCell(new Cell().add(new Paragraph("DUT Primary School\nITF\n22T_KHDL")
                .setTextAlignment(TextAlignment.RIGHT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setMarginRight(10f)
                .setBorder(null)
                .setFont(customFont)));

        float colWidth[] = {80, 300, 100, 80};
        Table peopleInfoTable = new Table(colWidth);
        peopleInfoTable.addCell(new Cell(0, 4).add(new Paragraph("People information")
                .setBold().setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph("Name").setBorder(null).setFont(customFont)));
        String name=people.getName();
        peopleInfoTable.addCell(new Cell().add(new Paragraph(TextUtils.removeDiacritics(name)).setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph("Invoice no.").setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph("123").setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph("Date").setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph(ld.toString()).setBorder(null).setFont(customFont)));
        peopleInfoTable.addCell(new Cell().add(new Paragraph("Role").setBorder(null).setFont(customFont)));
        String role=(account.getRole()==1)? "Admin":"Teacher";
        peopleInfoTable.addCell(new Cell().add(new Paragraph(role).setBorder(null).setFont(customFont)));

        Table itemInfoTable = createItemInfoTableFromJTable(table, customFont);

        document.add(new Paragraph("\n").setMarginBottom(20));
        document.add(headerTable);
        document.add(new Paragraph("\n").setMarginBottom(40));
        document.add(peopleInfoTable);
        document.add(new Paragraph("\n").setMarginBottom(50));
        document.add(itemInfoTable);
        document.add(new Paragraph("\n").setMarginBottom(20));
        // Add Terms and Conditions section
//        Paragraph termsParagraph = new Paragraph("Terms and Conditions")
//                .setFont(customFont)
//                .setBold()
//                .setFontSize(14)
//                .setMarginTop(20);
//        document.add(termsParagraph);
//
//        Paragraph termsContent = new Paragraph("1. Payment is due within 30 days.\n"
//                + "2. Late payments will incur a 5% late fee.\n"
//                + "3. Goods once sold will not be returned or exchanged.\n"
//                + "4. For any queries, please contact our customer service.")
//                .setFont(customFont)
//                .setFontSize(12);
//        document.add(termsContent);
//
//        // Add Sign section
        float signColWidth[] = {280, 280};
        Table signTable = new Table(signColWidth);
        signTable.addCell(new Cell().add(new Paragraph("\n\n\n\nPrincipal Signature:")
                .setFont(customFont).setBorder(null)));
        signTable.addCell(new Cell().add(new Paragraph("\n\n\n\nReport Issuer Signature:")
                .setFont(customFont).setTextAlignment(TextAlignment.LEFT).setBorder(null)));
        document.add(signTable);

        document.close();
        System.out.println("Created");
    }

    private static Table createItemInfoTableFromJTable(JTable jTable, PdfFont customFont) {
        float itemInfoColWidth[] = {100, 100, 100, 100, 100, 100};
        Table itemInfoTable = new Table(itemInfoColWidth);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            itemInfoTable.addCell(new Cell()
                    .add(new Paragraph(jTable.getColumnName(i))
                            .setBackgroundColor(new DeviceRgb(63, 169, 219))
                            .setFontColor(Color.WHITE)
                            .setFont(customFont))
            );
        }

        for (int row = 0; row < jTable.getRowCount(); row++) {
            for (int col = 0; col < jTable.getColumnCount(); col++) {
                itemInfoTable.addCell(new Cell().add(new Paragraph(TextUtils.removeDiacritics(jTable.getValueAt(row, col).toString()))
                        .setFont(customFont)));
            }
        }

        return itemInfoTable;
    }
}
