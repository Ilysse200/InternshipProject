package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.domain.ItemFlow;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@WebServlet("/pdfReport")
public class PDFServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type and the file name
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");


        try {
            generatePdfReport(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generatePdfReport(java.io.OutputStream outputStream) throws IOException {
        long totalSales = 0L;
        long totalPurchases = 0L;
        Long Balance = 0L;
        Long itemId = 0L;
        try (PdfWriter pdfWriter = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument)) {

            // Add content to the PDF document
            document.add(new Paragraph("Item Report").setBold().setFontSize(16f));
            document.add(new Paragraph("\n")); // Add some space

            // Add a table with column headers
            Table table = new Table(6); // 4 columns for "Description", "Quantity", "Price", "TotalPrice"
            table.addCell("Description").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell("Quantity").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell("Unit Price").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell("Total Price").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell("Transaction").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell("Dates").setBold().setTextAlignment(TextAlignment.CENTER);

            // Fetch data from the database
            ItemFlowDao itemFlowDao = new ItemFlowDao();
            List<ItemFlow> itemFlowList = itemFlowDao.displayAllEmployees();

            // Iterate through the data and add it to the table
            for (ItemFlow itemFlow : itemFlowList) {
                String itemName = itemFlow.getItemList().getItemName();
                int itemQuantity = itemFlow.getQuantity();
                int itemPrice = itemFlow.getUnitPrice();
                int totalPrice = itemFlow.getTotalPrice();
                String status = itemFlow.getStatus();
                LocalDate date = itemFlow.getItemFlowDate();

                // Add the data to the table
                table.addCell(itemName);
                table.addCell(String.valueOf(itemQuantity));
                table.addCell(String.valueOf(itemPrice));
                table.addCell(String.valueOf(totalPrice));
                table.addCell(String.valueOf(status));
                table.addCell(String.valueOf(date));
                if (itemFlow.getStatus().equals("IN")) {

                    totalPurchases += itemFlow.getQuantity();

                }
                else if(itemFlow.getStatus().equals("OUT")){
                    totalSales += itemFlow.getQuantity();
                }
                Balance =  totalPurchases - totalSales;

            }
            // Step 8: Add a separate row for total sales, total purchases, and balance
            table.addCell("Total quantity sold").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell(String.valueOf(totalSales)).setTextAlignment(TextAlignment.CENTER);
            table.addCell(""); // An empty cell for unit price column
            table.addCell(""); //An empty cell for total price column
            table.addCell(""); //An empty cell for transaction column
            table.addCell(""); //An empty cell for date column


            table.addCell("Total quantity purchased").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell(String.valueOf(totalPurchases)).setTextAlignment(TextAlignment.CENTER);
            table.addCell(""); // An empty cell for unit price column
            table.addCell(""); //An empty cell for total price column
            table.addCell(""); //An empty cell for transaction column
            table.addCell(""); //An empty cell for date column


            table.addCell("Quantity left").setBold().setTextAlignment(TextAlignment.CENTER);
            table.addCell(String.valueOf(Balance)).setTextAlignment(TextAlignment.CENTER);
            table.addCell(""); // An empty cell for unit price column
            table.addCell(""); //An empty cell for total price column
            table.addCell(""); //An empty cell for transaction column
            table.addCell(""); //An empty cell for date column




            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            System.out.println("PDF report generated successfully.");
        }
    }
}
