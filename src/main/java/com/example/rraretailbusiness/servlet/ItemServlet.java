package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.ItemDao;
import com.example.rraretailbusiness.domain.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/registerItem")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("itemName");
        String origin= req.getParameter("itemOrigin");
        String itemMeasure = req.getParameter("itemMeasure");
        String itemUnit = req.getParameter("itemUnit");
        String taxType = req.getParameter("itemTaxType");
        LocalDate regDate = LocalDate.parse(req.getParameter("registeredDate"));


        //save the item to the database
        //create an object to save the item. The object should match an existing constructor

        Item item = new Item(name, origin, itemMeasure, Long.valueOf(itemUnit), taxType, LocalDate.now());
        try{
            ItemDao dao = new ItemDao();
            dao.saveItem(item);
            sendSuccessMessage(resp, "Item saved successfully");
            return;

        }catch (Exception exception){
            exception.printStackTrace();
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
