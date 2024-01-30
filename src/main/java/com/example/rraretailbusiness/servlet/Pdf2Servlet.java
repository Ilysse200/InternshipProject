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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static jakarta.xml.bind.DatatypeConverter.parseDate;

@WebServlet("/purchasesReport")
public class Pdf2Servlet extends HttpServlet {
    private LocalDate startDate;
    private LocalDate endDate;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String startDateString = req.getParameter("startDate");
        String endDateString = req.getParameter("endDate");

        // Convert string dates to Date objects
         startDate = LocalDate.parse(startDateString);
         endDate = LocalDate.parse(endDateString);
        // Set the content type and the file name
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=report.pdf");


        try {
            generatePdfReport(resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        private void generatePdfReport (java.io.OutputStream outputStream) throws IOException {
            long totalSales = 0L;
            long totalPurchases = 0L;
            long quantitiesPurchased = 0L;
            Long Balance = 0L;
            Long itemId = 0L;
            try (PdfWriter pdfWriter = new PdfWriter(outputStream);
                 PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                 Document document = new Document(pdfDocument)) {

                // Add content to the PDF document
                document.add(new Paragraph("Purchase Report").setBold().setFontSize(16f));
                document.add(new Paragraph("\n")); // Add some space

                // Add a table with column headers
                Table table = new Table(5); // 4 columns for "Description", "Quantity", "Price", "TotalPrice"
                table.addCell("Date").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell("Description").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell("Quantity").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell("Unit Price").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell("Total Price").setBold().setTextAlignment(TextAlignment.CENTER);

                // Fetch data from the database
                ItemFlowDao itemFlowDao = new ItemFlowDao();
                List<ItemFlow> itemFlowList = itemFlowDao.displayAllEmployees();

                // Iterate through the data and add it to the table
                for (ItemFlow itemFlow : itemFlowList) {
                    if (isDateInRange(itemFlow.getItemFlowDate(), startDate, endDate) && itemFlow.getStatus().equals("IN")) {

                        int itemQuantity = itemFlow.getQuantity();
                        String itemName = itemFlow.getItemList().getItemName();
                        int itemPrice = itemFlow.getUnitPrice();
                        int totalPrice = itemFlow.getTotalPrice();
                        LocalDate datePurchased = itemFlow.getItemFlowDate();

                        totalPurchases +=itemFlow.getQuantity();
                        quantitiesPurchased +=itemFlow.getTotalPrice();


                        // Add the data to the table
                        table.addCell(String.valueOf(datePurchased));
                        table.addCell(itemName);
                        table.addCell(String.valueOf(itemQuantity));
                        table.addCell(String.valueOf(itemPrice));
                        table.addCell(String.valueOf(totalPrice));

                    }

                }
                // Step 8: Add a separate row for total sales, total purchases, and balance

                table.addCell(""); //An empty cell for total price column
                table.addCell("Total quantity purchased").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell(String.valueOf(totalPurchases)).setTextAlignment(TextAlignment.CENTER);
                table.addCell(""); // An empty cell for unit price column
                table.addCell(""); //An empty cell for total price column


                table.addCell(""); //An empty cell for total price column
                table.addCell("Total purchases").setBold().setTextAlignment(TextAlignment.CENTER);
                table.addCell(""); // An empty cell for quantity price column
                table.addCell(""); // An empty cell for unit price column
                table.addCell(String.valueOf(quantitiesPurchased)).setTextAlignment(TextAlignment.CENTER);


                // Add the table to the document
                document.add(table);

                // Close the document
                document.close();

                System.out.println("PDF report generated successfully.");
            }

        }


    private boolean isDateInRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
