package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.PurchaseDao;
import com.example.rraretailbusiness.dao.SalesDao;
import com.example.rraretailbusiness.domain.Purchase;
import com.example.rraretailbusiness.domain.Sales;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

@WebServlet("/pdfReports")
public class PDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type and the file name
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Add content to the PDF document
                float fontSize = 12;
                float leading = 1.5f * fontSize;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                // Fetch sales data from the database
                SalesDao salesDao = new SalesDao();
                List<Sales> salesList = salesDao.displayAllSales();

                PurchaseDao purchaseDao = new PurchaseDao();
                List<Purchase> purchases = purchaseDao.displayAllPurchases();

                contentStream.beginText();
                for (Sales sales : salesList) {
                    contentStream.newLineAtOffset(100, 700);

                    contentStream.showText("Sales ID of: " + sales.getSalesID());

                    // Use the leading to move to the next line with appropriate spacing
                    contentStream.newLineAtOffset(0, -leading);
                    contentStream.showText("Sales was made on : " + sales.getSalesDate());
                    contentStream.newLineAtOffset(0, -leading);
                    contentStream.newLineAtOffset(0, -leading);; // Add space between sales entries // Add space between sales entries
                }
                contentStream.newLine();
                contentStream.showText("--------------------------------------------------------");
                contentStream.newLineAtOffset(0, -leading);

               // Display Purchase data
                contentStream.showText("Purchase Data:");
                for (Purchase purchase : purchases) {
                    contentStream.newLineAtOffset(0, -leading);
                    contentStream.showText("Purchase ID: " + purchase.getPurchaseId());
                    contentStream.newLineAtOffset(0, -leading); // Move to the next line with appropriate spacing
                    contentStream.showText("Purchase Date: " + purchase.getPurchaseDate());
                    contentStream.newLine();

                }

            }

            // Save the document to the response output stream
            document.save(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}
