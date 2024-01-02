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
            Sales sales = new Sales();
            sales.setSalesDate(sale);


            // Assuming you want to associate the first employee from the list with the sales
            Customer customer = new Customer();
            for(Customer customer1: customers){
                if(customer1 !=null){
                    customer = customer1;
                }

            }
            sales.setCustomer(customer);



            Employee employee = new Employee();
            for(Employee employee1: employees){
                if(employee1 !=null){
                    employee = employee1;
                }
            }
            sales.setSalesExecuter(employee);

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
