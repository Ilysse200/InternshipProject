package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.*;
import com.example.rraretailbusiness.domain.*;
import com.example.rraretailbusiness.dao.CustomerDao;
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
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/registerSales")
public class SaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Get parameters from the request
            String dateSold = req.getParameter("salesDate");
            String customerId = req.getParameter("customerId");
            String salesExecuter= req.getParameter("salesExecuter");
//          String buttonClick = req.getParameter("submitButton");

            int sizeofArray;


            // Retrieve the JSON string from the request parameter
            String jsonTableData = req.getParameter("tableData");
            String jsonTableData1 = req.getParameter("itemNameData");
            System.out.println("Received JSON data: " + jsonTableData);



// Use Jackson ObjectMapper to convert the JSON string to a Java object
            ObjectMapper objectMapper = new ObjectMapper();
            Integer[] javaArray = objectMapper.readValue(jsonTableData, Integer[].class);
            String[] itemNamesArray = objectMapper.readValue(jsonTableData1, String[].class);

//            System.out.println("itemNames" + purchaseData.getItemNames());
//            System.out.println("other items" +purchaseData.getOtherData());
//
//// Access the arrays in the Java object
//            Integer[] otherDataArray = purchaseData.getOtherData();
//            String[] itemNamesArray = purchaseData.getItemNames();


            ItemDao itemDao = new ItemDao();
            Item item = new Item();

            //Create a list of all items
            List<Item> items = itemDao.displayAllEmployees();

            ItemFlowDao itemFlowDao = new ItemFlowDao();

//          String selectedItem = req.getParameter("itemList");


            // Validate parameters
            if (dateSold == null || dateSold.isEmpty() || customerId == null || salesExecuter == null) {
                sendErrorMessage(resp, "Invalid parameters");
//                resp.sendRedirect(req.getContextPath() + "/purchase.jsp");

            }

            // Parse parameters
            LocalDate salesDate = LocalDate.parse(dateSold);
            System.out.println(dateSold);
            Long customerIdLong = Long.parseLong(customerId);
            System.out.println(customerIdLong);
            Long empPurchaseIdLong = Long.parseLong(salesExecuter);
            System.out.println(empPurchaseIdLong);



            // Create and set Sales object
            Sales sales = new Sales();
            if(salesDate !=LocalDate.now())
                sales.setSalesDate(salesDate);

            // Set Customer and Employee for the Purchase
            CustomerDao customerDao = new CustomerDao();
            Customer customer = customerDao.findCustomerId(customerIdLong);
            if (customer == null) {
                sendErrorMessage(resp, "Customer not found");
                return;
            }
            sales.setCustomer(customer);

            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = employeeDao.findEmployeeId(empPurchaseIdLong);
            if (employee == null) {
                sendErrorMessage(resp, "Employee not found");
                return;
            }
            sales.setSalesExecuter(employee);

            // Save the Purchase
            SalesDao salesDao = new SalesDao();
            salesDao.saveSales(sales);
//            System.out.println("Sales record is " + purchase.getPurchaseId() + purchase.getSupplierId() + purchase.getEmpPurchase());

//            int sizeOfArray = itemNamesArray.length +


            //Create a list of itemFlows
            List<ItemFlow> itemFlows = new ArrayList<>();

            for (Item item1 : items) {
                boolean itemFound = false;

                for (int count = 0; count < itemNamesArray.length; count++) {
                    System.out.println("item1.getItemName(): " + item1.getItemName());
                    System.out.println("names: " + itemNamesArray[count]);
                    int arrayIndex = count * 4;

                    if (arrayIndex + 3 < javaArray.length) { // Ensure array bounds
                        if (item1.getItemName().equals(itemNamesArray[count])) {
                            // Item found in the itemNamesArray
                            itemFound = true;

                            ItemFlow itemFlow = new ItemFlow();
                            itemFlow.setStatus("OUT");
                            itemFlow.setQuantity(javaArray[arrayIndex]);
                            itemFlow.setUnitPrice(javaArray[arrayIndex + 1]);
                            itemFlow.setTotalPrice(javaArray[arrayIndex + 2]);
                            itemFlow.setQuantityAvailable(javaArray[arrayIndex + 3]);
                            itemFlow.setItemList(item1);
                            itemFlow.setItemFlowDate(salesDate);
                            itemFlows.add(itemFlow);

                            // No need to check further for this item
                            break;
                        }
                    }
                }

                // Check if the item was not found in itemNamesArray
                if (!itemFound) {
                    // Handle the case when the item is not in the itemNamesArray
                    System.out.println("Item not found in itemNamesArray for item: " + item1.getItemName());
                }
            }

// Print the number of itemFlows
            System.out.println("Number of itemFlows: " + itemFlows.size());

// Save the itemFlows
            ItemFlowDao dao = new ItemFlowDao();
            for (ItemFlow itemFlow : itemFlows) {
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
