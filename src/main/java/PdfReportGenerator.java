import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.domain.ItemFlow;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.List;

public class PdfReportGenerator {

    public static void main(String[] args) {
        try {
            generatePdfReport("report.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void generatePdfReport(String pdfFileName) throws FileNotFoundException {


        long totalSales = 0L;
        long totalPurchases = 0L;
        Long Balance = 0L;
        Long itemId = 0L;
        // Step 1: Create a PdfWriter object
        PdfWriter pdfWriter = new PdfWriter(pdfFileName);

        // Step 2: Create a PdfDocument object
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        // Step 3: Create a Document object
        Document document = new Document(pdfDocument);

        // Step 4: Add a header
        document.add(new Paragraph("Item Report").setBold().setFontSize(16f));
        document.add(new Paragraph("\n")); // Add some space

        // Step 5: Add a table with column headers
        Table table = new Table(4); // 4 columns for "Description", "Quantity", "Price", "TotalPrice"
        table.addCell("Description").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell("Quantity").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell("Unit Price").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell("Total Price").setBold().setTextAlignment(TextAlignment.CENTER);

        // Step 6: Fetch data from the database
        ItemFlowDao itemFlowDao = new ItemFlowDao();
        List<ItemFlow> itemFlowList = itemFlowDao.displayAllEmployees();

        // Step 7: Iterate through the data and add it to the table
        for (ItemFlow itemFlow : itemFlowList) {
            String itemName = itemFlow.getItemList().getItemName();
            int itemQuantity = itemFlow.getQuantity();
            int itemPrice = itemFlow.getUnitPrice();
            int totalPrice = itemFlow.getTotalPrice();

            // Add the data to the table
            table.addCell(itemName);
            table.addCell(String.valueOf(itemQuantity));
            table.addCell(String.valueOf(itemPrice));
            table.addCell(String.valueOf(totalPrice));
            if (itemFlow.getStatus().equals("IN")) {

                totalPurchases += itemFlow.getTotalPrice();

            }
            else if(itemFlow.getStatus().equals("OUT")){
                totalSales += itemFlow.getTotalPrice();
            }
            Balance = totalSales -totalPurchases;

        }
        // Step 8: Add a separate row for total sales, total purchases, and balance
        table.addCell("Total Sales").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell(""); // An empty cell for quantity column
        table.addCell(""); // An empty cell for unit price column
        table.addCell(String.valueOf(totalSales)).setTextAlignment(TextAlignment.CENTER);

        table.addCell("Total Purchases").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell(""); // An empty cell for quantity column
        table.addCell(""); // An empty cell for unit price column
        table.addCell(String.valueOf(totalPurchases)).setTextAlignment(TextAlignment.CENTER);

        table.addCell("Balance").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addCell(""); // An empty cell for quantity column
        table.addCell(""); // An empty cell for unit price column
        table.addCell(String.valueOf(Balance)).setTextAlignment(TextAlignment.CENTER);


        // Step 8: Add the table to the document
        document.add(table);

        // Step 9: Close the document
        document.close();

        System.out.println("PDF report generated successfully.");
    }
}

