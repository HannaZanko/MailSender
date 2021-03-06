package service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import entity.User;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDFConverter {

//    public static final String PATH_TO_FILE = "/opt/tomcat/apache-tomcat-9.0.58/webapp/resources/newPDF.pdf";
    public static final String PATH_TO_FILE = "C:\\Users\\ANYA\\IdeaProjects\\new_project2\\src\\main\\webapp\\resources\\newPDF.pdf";


    public static void convertToPdf() {

        System.out.println("method convert to pdf - start");
        JSONConverter jsonConverter = new JSONConverter();
        try {
            List<User> list = jsonConverter.parse();
            Map<String, List<String>> usersMap = new HashMap<>();
            for (User el : list) {
                usersMap.computeIfAbsent(el.getUserName(), k -> new ArrayList<String>()).add(el.getTimeToSpend() + "&#@" + el.getActivity());
            }

            PdfWriter pdfWriter = new PdfWriter(PATH_TO_FILE);
            float[] columnWidth = {200F, 100F, 200F};
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Document document = new Document(pdfDocument);
            Paragraph paragraph = new Paragraph("RED TEAM").setFontSize(50F)
                    .setFontColor(Color.RED);
            Paragraph date = new Paragraph(String.valueOf(formatter.format(new Date(System.currentTimeMillis()))))
                    .setFontSize(15F);
            document.add(paragraph).add(date);
            Table table = new Table(columnWidth);
            table.addCell(new Cell().add("User name"));
            table.addCell(new Cell().add("Spend time"));
            table.addCell(new Cell().add("Activities"));

            for (Map.Entry<String, List<String>> el : usersMap.entrySet()
            ) {
                table.addCell(new Cell(el.getValue().size(), 1).add(el.getKey()));
                for (String action : el.getValue()
                ) {
                    table.addCell(new Cell().add(action.split("&#@")[0]));
                    table.addCell(new Cell().add(action.split("&#@")[1]));
                }
            }

            document.add(table);
            System.out.println("method convert to pdf - end");
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
