package com.example.rraretailbusiness.servlet;

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

@WebServlet("/pdfReport")
public class PdfServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type and the file name
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=taxReport.pdf");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                float fontSize = 12;
                float leading = 1.5f * fontSize;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);

                // Retrieve data from the form
                String itemCode = request.getParameter("itemList");
                Long totalPrice = Long.parseLong(request.getParameter("totalPrice"));
                Long quantity = Long.parseLong(request.getParameter("quantity"));
                Long vat = Long.parseLong(request.getParameter("vat"));
                Long vatItem = Long.parseLong(request.getParameter("vatItem"));

                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);

                // Display form data in the PDF
                contentStream.showText("Item Code: " + itemCode);
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Total Price: " + totalPrice);
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Quantity: " + quantity);
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("VAT: " + vat);
                contentStream.newLineAtOffset(0, -leading);
                contentStream.showText("Total Price with VAT: " + vatItem);

                contentStream.endText();
            }

            // Save the document to the response output stream
            document.save(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}
