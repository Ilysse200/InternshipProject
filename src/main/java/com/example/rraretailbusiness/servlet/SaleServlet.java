package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.*;
import com.example.rraretailbusiness.domain.*;
import com.example.rraretailbusiness.dao.CustomerDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/registerSales")
public class SaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CustomerDao customerDao =  new CustomerDao();

        ItemDao itemDao = new ItemDao();

        EmployeeDao employeeDao = new EmployeeDao();

        String dateSales = req.getParameter("salesDate");
        List<Customer> customers= customerDao.displayAllCustomers() ;
        req.setAttribute("customers", customers);
        List<Item> items= itemDao.displayAllEmployees();
        req.setAttribute("items", items);
        List<Employee> employees = employeeDao.displayAllEmployees();
        req.setAttribute("employees", employees);

        if(dateSales !=null && !dateSales.isEmpty()) {
            LocalDate sale = LocalDate.parse(dateSales);

            // Assuming you want to associate the first employee from the list with the sales
            Customer customer = customers.isEmpty() ? null : customers.get(0);


            Sales sales = new Sales(sale, customer, items, employees);

            try {
                SalesDao salesDao = new SalesDao();
                salesDao.saveSales(sales);
                resp.sendRedirect(req.getContextPath() + "/ItemFlow.jsp");
            } catch (Exception exception) {
                exception.printStackTrace();
                sendErrorMessage(resp, "sales not recorded");
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
