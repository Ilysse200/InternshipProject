package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.*;
import com.example.rraretailbusiness.domain.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/registerItemFlow")
public class ItemFlowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //Create itemFlowDao instance
        ItemFlowDao itemFlowDao = new ItemFlowDao();

        //Get all itemFlows
        List<ItemFlow> itemFlows =itemFlowDao.displayAllEmployees();

        //Create itemDao instance
        ItemDao itemDao = new ItemDao();


        // Get the item ID from the request parameter
        Long itemId = Long.parseLong(req.getParameter("itemCode"));

        //Variable to store the id of the item we want to calculate for
        Long foundId = 0L;

        // Call your DAO method to get the total sales
        ItemFlowDao itemFlowDao1 = new ItemFlowDao();
        long totalSales = 0L;
        long totalPurchases = 0L;
        Long Balance = 0L;

        for (ItemFlow itemFlow : itemFlows) {
            Item item1 = itemDao.findItemId(itemId);
            // Add these print statements for debugging
//            System.out.println("itemId: " + itemId);
            System.out.println("item1.getItemCode(): " + item1.getItemCode());
            System.out.println("itemFlow.getItemList().getItemCode(): " + itemFlow.getItemList().getItemCode());

            // Add this line to compare the Long values directly
            System.out.println("Comparison result: " + item1.getItemCode().equals(itemFlow.getItemList().getItemCode()));
            if (itemId == itemFlow.getItemList().getItemCode() && itemFlow.getStatus().equals("IN")) {

                totalPurchases += itemFlow.getTotalPrice();

            }
            else if(itemId == itemFlow.getItemList().getItemCode() && itemFlow.getStatus().equals("OUT")){
                totalSales += itemFlow.getTotalPrice();
            }
            Balance = totalSales -totalPurchases;

        }
        // Now, you can use or display the 'totalSales' value as needed
        System.out.println("Total Sales for Item ID " + itemId + ": " + totalSales);
        System.out.println("Total Purchases for Item ID " + itemId + ": " + totalPurchases);
        System.out.println("Balance for Item ID " + itemId + ": " + Balance);


        // You may want to send the totalSales to the client or perform other actions...
        resp.sendRedirect(req.getContextPath() + "/result.jsp");
    }



        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }

        private void sendErrorMessage (HttpServletResponse resp, String message) throws IOException {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<script>alert('" + message + "');</script>");
        }
        private void sendSuccessMessage (HttpServletResponse resp, String message) throws IOException {
            resp.setContentType("text/html");
            PrintWriter pw = resp.getWriter();
            pw.println("<script>alert('" + message + "');</script>");
        }
    }

