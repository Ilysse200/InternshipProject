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



@WebServlet("/pdfReport")
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

                ItemDao itemDao = new ItemDao();
                List<Item> itemList = itemDao.displayAllEmployees();

                ItemFlow itemFlow = new ItemFlow();
                ItemFlowDao itemFlowDao = new ItemFlowDao();

                List<ItemFlow> sales = itemFlowDao.displayAllEmployees();

                int sizeSales = salesList.size();

                float yPosition = 700;  // Starting Y position
                contentStream.beginText();
//                for(int i=0; i< sizeSales; i++){
                for (ItemFlow itemFlow1 : sales) {
                    contentStream.newLineAtOffset(100, 700);

                    contentStream.showText("Sales made on: " + itemFlow1.getItemFlowDate());

                    // Use the leading to move to the next line with appropriate spacing
                    contentStream.newLineAtOffset(0, -leading);
                    if ("OUT".equals(itemFlow1.getStatus())) {
                        contentStream.showText("Item Sold: " + itemFlow1.getItemList().getItemName() +
                                "For quantity: " + itemFlow1.getQuantity() + " Total price is : " +
                                itemFlow1.getTotalPrice());
                        contentStream.newLineAtOffset(0, -leading);

                        Long totalSales = 0L;
                        totalSales += itemFlow1.getTotalPrice();

                        contentStream.showText("Total Sales made is: " + totalSales);
                    }
//                    contentStream.showText("Sales was made on : " + sales.getSalesDate());
                    contentStream.newLine();
                    contentStream.newLineAtOffset(0, -leading);
                    contentStream.newLineAtOffset(0, -2 * leading);
                    ; // Add space between sales entries // Add space between sales entries

                    // Move to the next section
                    yPosition -= 4 * leading;

                    // Display Purchase data
                    contentStream.showText("Purchase Data:");
                    for (Purchase purchase : purchases) {
                        contentStream.newLineAtOffset(0, -leading);
                        contentStream.showText("Purchase ID: " + purchase.getPurchaseId());
                        contentStream.newLineAtOffset(0, -leading); // Move to the next line with appropriate spacing
                        contentStream.showText("Purchase Date: " + purchase.getPurchaseDate());
                        // Move to the next line with appropriate spacing
                        contentStream.newLineAtOffset(0, -2 * leading);

                    }
                    // Move to the next section
                    yPosition -= 4 * leading;

                    //Display Items
                    contentStream.showText("Item Information");
                    Long quantity = 0L;
                    Long vatItem = 0L;
                    Long price = 0L;
                    Long vat = 0L;

                    for (Item item : itemList) {
                        if ("box".equals(item.getItemMeasure())) {
                            quantity = 5L;
                            System.out.println("Item Unit: " + item.getItemUnit());
                            System.out.println("Item Measure: " + item.getItemMeasure());
                            price = 0L + (Long.valueOf(item.getItemUnit()) * quantity);
                            vat = (long) (0.18 * price);
                            vatItem = vat + price;
                            contentStream.newLineAtOffset(0, -leading);

                        } else if ("pack".equals(item.getItemMeasure())) {
                            quantity = 10L;
                            System.out.println("Item Unit: " + item.getItemUnit());
                            System.out.println("Item Measure: " + item.getItemMeasure());
                            price = 0L + (Long.valueOf(item.getItemUnit()) * quantity);
                            vat = (long) (0.18 * price);
                            vatItem = vat + price;
                            // Move to the next line with appropriate spacing
                            contentStream.newLineAtOffset(0, -leading);

                        }

                        contentStream.newLine();

                    }
                    // Move to the next section
                    yPosition -= 4 * leading;

//                    contentStream.showText("Total tax: " + vatItem);
                    contentStream.newLine();

                }
            }


            // Save the document to the response output stream
            document.save(response.getOutputStream());
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}
