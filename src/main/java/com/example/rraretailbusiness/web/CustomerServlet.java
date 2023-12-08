package com.example.rraretailbusiness.web;

import com.example.rraretailbusiness.dao.CustomerDao;
import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.domain.Customer;
import com.example.rraretailbusiness.domain.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/")
public class CustomerServlet {


        public void init() throws ServletException {

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getServletPath();
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCustomer(request, response);
                    break;

                case "/delete":
                    deleteCustomer(request, response);
                    break;

                case "/update":
                    updateCustomer(request, response);
                    break;
                default:
                    break;

            }
        }

        private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher(("user-form.jsp"));
            dispatcher.forward(request, response);
        }

        private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {



            String name = request.getParameter("customerName");
            String mobileNumber = request.getParameter("customerMail");
            String mail = request.getParameter("customerAddress");
            String address = request.getParameter("customerAddress");
            String tin = request.getParameter("customerTin");
            try {
                CustomerDao customerDao = new CustomerDao();
                Customer customer = new Customer(name, mobileNumber, mail, address, tin);

//                employeeDao.saveEmployee(employee);
                response.sendRedirect("list");


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
            try {
                Long Id = Long.valueOf(request.getParameter("Id"));
              CustomerDao customerDao = new CustomerDao();
                customerDao.deleteCustomer(Id);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("list");
            }

        }

        //update employee
        public void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

            Long Id = Long.valueOf(request.getParameter("id"));
            Customer customer = new Customer();

            String name = request.getParameter("customerName");
            String mobileNumber = request.getParameter("customerMail");
            String mail = request.getParameter("customerAddress");
            String address = request.getParameter("customerAddress");
            String tin = request.getParameter("customerTin");
            try {

                CustomerDao customerDao = new CustomerDao();
                customerDao.UpdateCustomer(customer, Id);
                response.sendRedirect("list");
            } catch (Exception exception) {
                exception.printStackTrace();

            }
        }

        public void displayCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{

            CustomerDao dao = new CustomerDao();
            dao.displayAllCustomers();
            response.sendRedirect("home");

        }
    }

