package com.example.rraretailbusiness.web;


import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.domain.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;



@WebServlet("/registerEmployee")
public class EmployeeServlet extends HttpServlet {
    String url = "/EmployeeDetails.jsp";
    private static  final Logger logger = Logger.getLogger(EmployeeServlet.class.getName());

    public void init() throws ServletException {
        try {
            InputStream config = getClass().getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(config);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading logging configuration", e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);


        String Fname = request.getParameter("empFirstName");
        String Lname = request.getParameter("empLastName");
        String Email = request.getParameter("empMail");
        LocalDate Dob = LocalDate.parse(request.getParameter("empDOB"));
        String Password = request.getParameter("empPassword");
        String Username = request.getParameter("empUserName");
        try {
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = new Employee(Fname, Lname, Username, Password, Email, Dob);

            employeeDao.saveEmployee(employee);
            response.sendRedirect(request.getContextPath() + "/EmployeeDetails.jsp");


        } catch (Exception ex) {
           logger.log(Level.SEVERE, "An error has occured", ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeDetails");
        dispatcher.forward(request, response);

    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//        switch (action) {
//            case "/new":
//                showNewForm(request, response);
//                break;
////            case "/insert":
////                insertEmployee(request, response);
////                break;
//
//            case "/delete":
//                deleteEmployee(request, response);
//                break;
//
//            case "/update":
//                updateEmployee(request, response);
//                break;
//            default:
//                displayEmployee(request,response);
//                break;
//
//        }
//        RequestDispatcher dispatcher = request.getRequestDispatcher("../webapp/EmployeeDetails.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher(("user-form.jsp"));
//        dispatcher.forward(request, response);
//    }

//    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//
//        String Fname = request.getParameter("empFirstName");
//        String Lname = request.getParameter("empLastName");
//        String Email = request.getParameter("empMail");
//        LocalDate Dob = LocalDate.parse(request.getParameter("empDOB"));
//        String Password = request.getParameter("empPassword");
//        String Username = request.getParameter("empUserName");
//        try {
//            EmployeeDao employeeDao = new EmployeeDao();
//            Employee employee = new Employee(Fname, Lname, Username, Password, Email, Dob);
//
//            employeeDao.saveEmployee(employee);
//            response.sendRedirect("list");
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        // Forward the request to the success page
//        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeDetails.jsp");
//        dispatcher.forward(request, response);
//
//    }

//    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//            Long Id = Long.valueOf(request.getParameter("Id"));
//            EmployeeDao employeeDao = new EmployeeDao();
//            employeeDao.deleteEmployee(Id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("list");
//        }
//
//    }
//
//    //update employee
//    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Long Id = Long.valueOf(request.getParameter("id"));
//        Employee employee = new Employee();
//
//        String Fname = request.getParameter("empFirstName");
//        String Lname = request.getParameter("empLastName");
//        String Email = request.getParameter("empMail");
//        LocalDate Dob = LocalDate.parse(request.getParameter("empDOB"));
//        String Password = request.getParameter("empPassword");
//        String Username = request.getParameter("empUserName");
//
//
//        EmployeeDao employeeDao = new EmployeeDao();
//        employeeDao.UpdateEmployee(employee, Id);
//
//        response.sendRedirect("list");
//    }
//
//    public void displayEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        try {
//
//            EmployeeDao dao = new EmployeeDao();
//            List<Employee> employeeList = dao.displayAllEmployees();
//            request.setAttribute("listEmployee", employeeList);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("employeeList.jsp");
//            response.sendRedirect("home");
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
}


