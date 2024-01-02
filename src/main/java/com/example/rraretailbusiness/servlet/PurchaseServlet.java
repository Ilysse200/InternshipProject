package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.dao.ItemDao;
import com.example.rraretailbusiness.dao.PurchaseDao;
import com.example.rraretailbusiness.dao.SupplierDao;
import com.example.rraretailbusiness.domain.Employee;
import com.example.rraretailbusiness.domain.Item;
import com.example.rraretailbusiness.domain.Purchase;
import com.example.rraretailbusiness.domain.Supplier;
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

@WebServlet("/registerPurchase")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        SupplierDao supplierDao =  new SupplierDao();

        ItemDao itemDao = new ItemDao();

        EmployeeDao employeeDao = new EmployeeDao();

        String datePurchase = req.getParameter("purchaseDate");
        List<Supplier> suppliers= supplierDao.displayAllSuppliers() ;
        req.setAttribute("suppliers", suppliers);
        List<Item> items= itemDao.displayAllEmployees();
        req.setAttribute("items", items);
        List<Employee> employees = employeeDao.displayAllEmployees();
        req.setAttribute("employees", employees);
        Purchase purchase = new Purchase();

        if(datePurchase !=null && !datePurchase.isEmpty()) {
            LocalDate purchases = LocalDate.parse(datePurchase);
            purchase.setPurchaseDate(purchases);

            Supplier supplier = new Supplier();
            for(Supplier supplier1: suppliers){
                if(supplier1 !=null){
                    supplier = supplier1;
                }

            }
            purchase.setSupplierId(supplier);

            Item item = new Item();
            for(Item item1: items){
                if(item1 !=null){
                    item = item1;
                }

            }
            purchase.setItems(item);


            Employee employee = new Employee();
            for(Employee employee1: employees){
                if(employee1 !=null){
                    employee = employee1;
                }
            }
            purchase.setEmpPurchase(employee);



            try {
                PurchaseDao purchaseDao = new PurchaseDao();
                purchaseDao.savePurchase(purchase);
                System.out.println("After saving purchase");
                resp.sendRedirect(req.getContextPath() + "/ItemFlow.jsp");

            } catch (Exception exception) {
                exception.printStackTrace();
                sendErrorMessage(resp, "purchase not recorded");
            }
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