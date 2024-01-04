package com.example.rraretailbusiness.servlet;

import com.example.rraretailbusiness.dao.ItemDao;
import com.example.rraretailbusiness.domain.Item;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet("/getItemDetails")
public class ItemDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Item item = new Item();

        String itemCode = request.getParameter("itemCode");


//        ItemDao itemDao = new ItemDao();
//        Item item = new Item();
//        Item SelectedItem = new Item();






//        if()
//
//
//        for(count=0; count<){
//

        }
//        int sizeItems = items.size();
//        for (int count = 0; count < sizeItems; count++) {
//            for (Item item : items) {
//
//                // Create a JSON object with item details
//                JSONObject itemDetails = new JSONObject();
//                try {
//                    itemDetails.put("itemCode", item.getItemCode());
//                    itemDetails.put("itemName", item.getItemName());
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//
//
//                // Set the content type and write the JSON response
//                response.setContentType("application/json");
//                response.getWriter().write(itemDetails.toString());
//            }
//        }
//    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
