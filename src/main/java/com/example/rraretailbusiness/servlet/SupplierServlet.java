package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.CustomerDao;
import com.example.rraretailbusiness.dao.SupplierDao;
import com.example.rraretailbusiness.domain.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerSupplier")
public class SupplierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("supplierName");
        String mobile= req.getParameter("supplierTel");
        String email= req.getParameter("supplierEmail");
        String address = req.getParameter("supplierAddress");
        String tin = req.getParameter("supplierTin");

        Supplier supplier = new Supplier(name, mobile, email, address, tin);


        // Validation of the supplier tin

        if(!tin.matches("^1\\d{8}$") || !isTinUnique(tin) || name.trim().isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/Error.jsp");
            return;
        }
            try{
                SupplierDao supplierDao = new SupplierDao();
                supplierDao.saveSupplier(supplier);
                sendSuccessMessage(resp, "Supplier registered successfully");
                resp.sendRedirect(req.getContextPath() + "/Home.jsp");

            }catch (Exception exception){
                exception.printStackTrace();
                System.out.println(exception.toString());
                sendErrorMessage(resp, "supplier not saved");
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
    private boolean isTinUnique(String tin){

        //check whether the tin already exist in the database
        CustomerDao customerDao = new CustomerDao();
        return customerDao.isTinUnique(tin);
    }
}
