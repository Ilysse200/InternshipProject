package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.domain.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/loginEmployee")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get printWriter
        PrintWriter pw = resp.getWriter();
        //set the content type
        resp.setContentType("text/html");
        String Username = req.getParameter("empUserName");
        String Password = req.getParameter("empPassword");

        Employee employee = new Employee(Username, Password);


        //Check whether the data provided is the same as the data from the database

        //Instantiate the dao to get access to the data from  the database

        EmployeeDao emp = new EmployeeDao();
        Employee checkEmployee1 = new Employee();

        boolean loginsuccessful = false;

        List<Employee> employees= emp.displayAllEmployees();
        for(Employee employee1: employees){
            if(employee1.getEmpUserName().trim().equals(employee.getEmpUserName()) && employee1.getEmpPassword().trim().equals(employee.getEmpPassword())){
                sendSuccessMessage(resp,"Login was successful");
                //check whether the employee username and password are not null
//                System.out.println(employee1.getEmpUserName().toString());
//                System.out.println(employee1.getEmpPassword().toString());
                loginsuccessful=true;
                resp.sendRedirect(req.getContextPath() + "/Home.jsp");
            }
            }
        if(!loginsuccessful){
            sendErrorMessage(resp, "Login was unsuccessful");

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
