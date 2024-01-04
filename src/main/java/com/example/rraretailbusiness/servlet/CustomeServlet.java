package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.CustomerDao;
import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.domain.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerCustomer")
public class CustomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        insertCustomer(request, response);

//        String action = request.getServletPath();
//        switch (action) {
//            case "/new":
//                showNewForm(request, response);
//                break;
//            case "/insert":
//                insertCustomer(request, response);
//                break;
//
//            case "/delete":
//                deleteCustomer(request, response);
//                break;
//
//            case "/update":
//                updateCustomer(request, response);
//                break;
//            default:
//                break;
//
//        }
    }

//    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(("user-form.jsp"));
//        dispatcher.forward(request, response);
//    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {



        String name = request.getParameter("customerName");
        String mobileNumber = request.getParameter("customerMail");
        String mail = request.getParameter("customerAddress");
        String address = request.getParameter("customerAddress");
        String tin = request.getParameter("customerTin");

        // check if the tin is exactly 9 digits and tin is unique

        if(!tin.matches("^1\\d{8}$") || !isTinUnique(tin)){
            sendErrorMessage(response, "The tin or phone number is invalid");
            return;
        }
        try {
            CustomerDao customerDao = new CustomerDao();
            Customer customer = new Customer(name, mobileNumber, mail, address, tin);

//          employeeDao.saveEmployee(employee);
            customerDao.saveCustomer(customer);
            sendSuccessMessage(response, "customer registered successfully");
            response.sendRedirect(request.getContextPath() + "/Home.jsp");


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//
//    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            Long Id = Long.valueOf(request.getParameter("Id"));
//            CustomerDao customerDao = new CustomerDao();
//            customerDao.deleteCustomer(Id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("list");
//        }
//
//    }
//
//    //update employee
//    public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Long Id = Long.valueOf(request.getParameter("id"));
//        Customer customer = new Customer();
//
//        String name = request.getParameter("customerName");
//        String mobileNumber = request.getParameter("customerMail");
//        String mail = request.getParameter("customerAddress");
//        String address = request.getParameter("customerAddress");
//        String tin = request.getParameter("customerTin");
//        try {
//
//            CustomerDao customerDao = new CustomerDao();
//            customerDao.UpdateCustomer(customer, Id);
//            response.sendRedirect("list");
//        } catch (Exception exception) {
//            exception.printStackTrace();
//
//        }
//    }
//
//    public void displayCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
//
//        CustomerDao dao = new CustomerDao();
//        dao.displayAllCustomers();
//        response.sendRedirect("home");
//
//    }


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
    private boolean isTinUnique(String tin){

        //check whether the tin already exist in the database
        CustomerDao customerDao = new CustomerDao();
        return customerDao.isTinUnique(tin);
    }
}
