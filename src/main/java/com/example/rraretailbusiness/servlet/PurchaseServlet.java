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
import java.util.ArrayList;
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
            String buttonClick = req.getParameter("submitButton");

//            String selectedItem = req.getParameter("itemList");






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

            // Check whether the button was clicked
            if (buttonClick != null) {

                // Save multiple items in the itemFlow table

                Item item = new Item();

                ItemFlowDao itemFlowDao = new ItemFlowDao();


                ItemDao itemDao = new ItemDao();

                //create an item List
                List<Item> items = itemDao.displayAllEmployees();
                req.setAttribute("itemName", items);
                for (Item item1 : items) {
                    if (item1 != null) {
                        ItemFlow itemFlow = new ItemFlow();
                        itemFlow.setStatus("IN");
                        itemFlow.setItemFlowsalesID(null);
                        itemFlow.setItemList(item1);
                        itemFlow.setPurchasesItemFlow(purchase);
                        itemFlow.setItemFlowDate(purchaseDate);


                        //save the itemFlow
                        itemFlowDao.saveItemFlow(itemFlow);
                    }

                }
            }





            // Redirect to a success page
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