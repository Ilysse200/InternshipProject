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

        ItemDao itemDao = new ItemDao();
        SalesDao salesDao = new SalesDao();
        PurchaseDao purchaseDao = new PurchaseDao();
        ItemFlowDao itemFlowDao = new ItemFlowDao();


        LocalDate flowDate = LocalDate.parse(req.getParameter("itemFlowDate"));
        List<Purchase> purchasesItemFlow = purchaseDao.displayAllPurchases();
        req.setAttribute("purchases", purchasesItemFlow);

        List<Sales> itemFlowsalesID = salesDao.displayAllSales();
        req.setAttribute("sales", itemFlowsalesID);
        List<Item> itemList = itemDao.displayAllEmployees();
        req.setAttribute("items", itemList);


        Purchase purchaseItem = purchasesItemFlow.isEmpty() ? null : purchasesItemFlow.get(0);
        Sales itemSales = itemFlowsalesID.isEmpty() ? null : itemFlowsalesID.get(0);
        Item item = itemList.isEmpty() ? null : itemList.get(0);

        // ...

        // Assuming you want to handle either itemSales or purchaseItem, not both
        ItemFlow itemFlow = new ItemFlow(flowDate, purchaseItem, itemSales, item);
        itemFlow.setItemFlowDate(flowDate);

        if (!itemFlowsalesID.isEmpty()) {
            // Handle the case where there is at least one itemSales
            itemFlow.setItemFlowsalesID(itemFlowsalesID.get(0));
            itemFlow.setPurchasesItemFlow(null);
//            itemFlowDao.saveItemFlow(itemFlow);
        } else if (!purchasesItemFlow.isEmpty()) {
            // Handle the case where there is at least one purchaseItem
            itemFlow.setItemFlowsalesID(null);
            itemFlow.setPurchasesItemFlow(purchasesItemFlow.get(0));
        } else {
            // Handle the case where both lists are empty or any other specific logic
            sendErrorMessage(resp, "No sales or purchases available");
            return;
        }

        // Set the itemList (if needed)
        if (!itemList.isEmpty()) {
            itemFlow.setItemList(itemList.get(0));
        }

        // Save the itemFlow
        itemFlowDao.saveItemFlow(itemFlow);
        sendSuccessMessage(resp, "itemFlow saved successfully");
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

