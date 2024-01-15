package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.ItemDao;
import com.example.rraretailbusiness.dao.ItemFlowDao;
import com.example.rraretailbusiness.dao.PurchaseDao;
import com.example.rraretailbusiness.dao.SalesDao;
import com.example.rraretailbusiness.domain.Item;
import com.example.rraretailbusiness.domain.ItemFlow;
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

@WebServlet("/salesReport")
public class purchaseReport extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // Set the content type and the file name
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=report.pdf");

        try (
                PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Add content to the PDF document
                float fontSize = 12;
                float leading = 1.5f * fontSize;
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);

                ItemFlowDao itemFlowDao = new ItemFlowDao();
                List<ItemFlow> itemFlows = itemFlowDao.displayAllEmployees();

                float yPosition = 700;  // Starting Y position
                contentStream.beginText();

                for (ItemFlow itemFlow1 : itemFlows) {
                    contentStream.newLineAtOffset(100, yPosition);

                    contentStream.showText("Sales made on date: " + itemFlow1.getItemFlowDate());
                    contentStream.newLineAtOffset(0, 2*leading);

                    if ("OUT".equals(itemFlow1.getStatus())) {
                        contentStream.showText("Item Sold: " + itemFlow1.getItemList().getItemName() +
                                "For quantity: " + itemFlow1.getQuantity() + " Total price is : " +
                                itemFlow1.getTotalPrice());
                        contentStream.newLineAtOffset(0, -leading);

                        Long totalSales = 0L;
                        totalSales += itemFlow1.getTotalPrice();

                        contentStream.showText("Total Sales made is: " + totalSales);
                    }

                    yPosition -= 20; // Adjust the Y position for the next line
                }

                contentStream.endText();
                contentStream.close(); // Close the content stream

                // Save the document to the response output stream
                document.save(response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}


