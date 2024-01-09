package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.*;
import com.example.rraretailbusiness.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebServlet("/registerPurchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Get parameters from the request
            String datePurchase = req.getParameter("purchaseDate");
            String supplierId = req.getParameter("supplierId");
            String empPurchaseId = req.getParameter("empPurchase");
//          String buttonClick = req.getParameter("submitButton");

            int sizeofArray;


            // Retrieve the JSON string from the request parameter
            String jsonTableData = req.getParameter("tableData");

            // Use Jackson ObjectMapper to convert the JSON string to a Java array
            ObjectMapper objectMapper = new ObjectMapper();
            String[] javaArray = objectMapper.readValue(jsonTableData, String[].class);

            ItemDao itemDao = new ItemDao();
            Item item = new Item();

            //Create a list of all items
            List<Item> items = itemDao.displayAllEmployees();

            ItemFlowDao itemFlowDao = new ItemFlowDao();

//          String selectedItem = req.getParameter("itemList");


            // Validate parameters
            if (datePurchase == null || datePurchase.isEmpty() || supplierId == null || empPurchaseId == null) {
                sendErrorMessage(resp, "Invalid parameters");
//                resp.sendRedirect(req.getContextPath() + "/purchase.jsp");

            }

            // Parse parameters
            LocalDate purchaseDate = LocalDate.parse(datePurchase);
            System.out.println(datePurchase);
            Long supplierIdLong = Long.parseLong(supplierId);
            System.out.println(supplierIdLong);
            Long empPurchaseIdLong = Long.parseLong(empPurchaseId);
            System.out.println(empPurchaseIdLong);



            // Create and set Purchase object
            Purchase purchase = new Purchase();
            if(purchaseDate !=LocalDate.now())
            purchase.setPurchaseDate(purchaseDate);

            // Set Supplier and Employee for the Purchase
            SupplierDao supplierDao = new SupplierDao();
            Supplier supplier = supplierDao.findSupplierId(supplierIdLong);
            if (supplier == null) {
                sendErrorMessage(resp, "Supplier not found");
                return;
            }
            purchase.setSupplierId(supplier);

            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.findEmployeeId(empPurchaseIdLong);
            if (employee == null) {
                sendErrorMessage(resp, "Employee not found");
                return;
            }
            purchase.setEmpPurchase(employee);

            // Save the Purchase
            PurchaseDao purchaseDao = new PurchaseDao();
            purchaseDao.savePurchase(purchase);
            System.out.println("Purchase record is " + purchase.getPurchaseId() + purchase.getSupplierId() + purchase.getEmpPurchase());


            //Create a list of itemFlows
            List<ItemFlow> itemFlows = new ArrayList<>();

                for(Item item1: items) {
                    for (int count = 0; count < javaArray.length; count++) {
                        System.out.println("item1.getItemName(): " + item1.getItemName());
                        System.out.println("names: " + javaArray[count]);
                        if (item1.getItemName().equals(javaArray[count])) {
                            ItemFlow itemFlow = new ItemFlow();
                            itemFlow.setStatus("IN");
                            itemFlow.setItemList(item1);
                            itemFlows.add(itemFlow);
                            itemFlow.setItemFlowDate(purchaseDate);
                            break;

                        }
                    }
                }
//                    return;

                //print the number of itemFlows
            System.out.println("Number of itemFlows: " + itemFlows.size());


            //Save the itemFlows
            ItemFlowDao dao = new ItemFlowDao();
            for(ItemFlow itemFlow: itemFlows){
                dao.saveItemFlow(itemFlow);
                System.out.println("Number of itemFlows: " + itemFlows.size());

            }


           resp.sendRedirect(req.getContextPath() + "/Home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            sendErrorMessage(resp, "Error occurred: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void sendErrorMessage(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<script>alert('" + message + "');</script>");
    }

    private void sendSuccessMessage(HttpServletResponse resp, String message) throws IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<script>alert('" + message + "');</script>");
    }
}