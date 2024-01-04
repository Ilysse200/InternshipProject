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

        String clickButton = req.getParameter("addButton");

        List<Customer> customers= customerDao.displayAllCustomers() ;
        req.setAttribute("customers", customers);

        List<Item> items= itemDao.displayAllEmployees();
        req.setAttribute("items", items);

        List<Employee> employees = employeeDao.displayAllEmployees();
        req.setAttribute("employees", employees);



        if(dateSales !=null && !dateSales.isEmpty() && LocalDate.parse(dateSales) != LocalDate.now()) {
            System.out.println(LocalDate.now());
            LocalDate sale = LocalDate.parse(dateSales);
            Sales sales = new Sales();
            sales.setSalesDate(sale);


            Customer customer = new Customer();
            for(Customer customer1: customers){
                if(customer1 !=null){
                    customer = customer1;
                }

            }
            sales.setCustomer(customer);

            if(clickButton !=null){
                ItemFlow itemFlow = new ItemFlow();

                ItemFlowDao itemFlowDao = new ItemFlowDao();
                for(Item item: items){
                    if(item !=null){
                        itemFlow.setItemList(item);
                        itemFlow.setStatus("OUT");
                        itemFlow.setPurchasesItemFlow(null);
                        itemFlow.setItemFlowDate(sale);
                        itemFlow.setItemFlowsalesID(sales);


                        //Save the itemFLow
                        itemFlowDao.saveItemFlow(itemFlow);
                    }
                }

            }



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
