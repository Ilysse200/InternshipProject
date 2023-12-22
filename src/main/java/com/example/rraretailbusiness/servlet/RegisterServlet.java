package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.EmployeeDao;
import com.example.rraretailbusiness.dao.HibernateUtil;
import com.example.rraretailbusiness.domain.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/registerEmployee")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get printWriter
        PrintWriter pw = resp.getWriter();
        //set the content type
        resp.setContentType("text/html");
        // Get the employee information

        String Fname = req.getParameter("empFirstName");
        String Lname = req.getParameter("empLastName");
        String Email = req.getParameter("empMail");
        LocalDate Dob = LocalDate.parse(req.getParameter("empDOB"));
        String Password = req.getParameter("empPassword");
        String Username = req.getParameter("empUserName");

        // Calculate age based on the current date
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - Dob.getYear();

        // Check if the employee is 18 years or older
        if (age < 18) {
            sendErrorMessage(resp,"Employee must be 18 years or older to register");
            return; // Do not proceed with saving the employee record
        }

        //Make sure the email contain "@"
        if (!Email.contains("@") && Email.trim().isEmpty()){
            sendErrorMessage(resp,"Invalid email address");
            return; // Do not proceed with saving the employee record
        }

        //Make sure password characters are greater than 7

        if (Password.isEmpty() && Password.length()<7){
            sendErrorMessage(resp,"Password is empty or too short");
            return; // Do not proceed with saving the employee record
        }

        // check whether the username is unique
        if(!isUsernameUnique(Username)){
            sendErrorMessage(resp, "Username already in use. Please choose another username.");
            return;
        }


        Employee employee = new Employee(Fname, Lname, Username, Password, Email, Dob);
        //Check whether the data can be read
        System.out.println(employee);

        EmployeeDao employeeDao = new EmployeeDao();

        try{
            employeeDao.saveEmployee(employee);
            sendSuccessMessage(resp, "Record Is Registered Successfully");
            // Redirect to the login page after successful registration
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } catch (HibernateException he) {
            he.printStackTrace();
            sendErrorMessage(resp,"Record not Registered Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h2>" + e.getMessage() + "</h2>");
        }

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
    private boolean isUsernameUnique(String Username){

        //check whether the username already exist in the database
        EmployeeDao employeeDao = new EmployeeDao();
        return employeeDao.isUsernameUnique(Username);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
