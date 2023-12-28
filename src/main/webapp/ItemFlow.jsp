<%@ page import="com.example.rraretailbusiness.dao.ItemDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rraretailbusiness.dao.PurchaseDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Purchase" %>
<%@ page import="com.example.rraretailbusiness.dao.SalesDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Sales" %><%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 27/12/2023
  Time: 3:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%-- Fetch all items and their details from the server --%>
<%-- Fetch all items and their details from the server --%>
<%
    ItemDao itemDao = new ItemDao();
    List<Item> items = itemDao.displayAllEmployees();
%>

<script>
    var itemDetails = {};

    <%
        // Simulated calculation for total price and quantity (replace with actual logic)
        for (Item item : items) {
            Long totalPrice = Long.valueOf(item.getItemUnit()) * 5L;
            Long quantity = 5L;
            Long vat= totalPrice*(18/100);
            Long vatItem= totalPrice + vat;
    %>

    itemDetails['<%= item.getItemCode() %>'] = {
        totalPrice: <%= totalPrice %>,
        quantity: <%= quantity %>,
        vat: <%= vat %>,
        vatItem: <%= vatItem%>
    };

    <%
        }
    %>
</script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">ItemFlow Record</div>
                <div class="card-body">
                    <form method="POST" action="registerItemFlow">
                        <div class="mb-3">
                            <label for="date" class="form-label">itemFlowDate</label>
                            <input type="date" name="itemFlowDate" class="form-control" id="date" >
                        </div>
                        <div class="mb-3">
                            <label for="purchases" class="form-label">purchasesItemFlow</label>
                            <select class="form-select" name="purchasesItemFlow" id="purchases" >
                                <%!
                                    private static final Object IN = "IN";
                                    private static final Object OUT = "OUT";
                                %><%
                                    // Fetch purchases from the database using the DAO
                                    PurchaseDao purchaseDao = new PurchaseDao();
                                    List<Purchase> purchases = purchaseDao.displayAllPurchases();

                                    // Iterate over the purchases and generate <option> elements
                                    for (Purchase purchase : purchases) {
                                %>
                                <option value="<%= purchase.getPurchaseId() %>"><%= OUT %></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="ItemFlowsales" class="form-label">ItemFlowsales</label>
                            <select class="form-select" name="ItemFlowsalesID" id="ItemFlowsales" >
                                <%
                                    // Fetch sales from the database using the DAO
                                    SalesDao salesDao = new SalesDao();
                                    List<Sales> sales = salesDao.displayAllSales();

                                    // Iterate over the sales and generate <option> elements
                                    for (Sales sales1 : sales) {
                                %>
                                <option value="<%= sales1.getSalesID() %>"><%= IN %></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="item2" class="form-label">itemList</label>
                            <select class="form-select" name="itemList" id="item2" >
                                <%
                                    // Fetch items from the database using the DAO
                                    ItemDao itemsDao = new ItemDao();
                                    List<Item> items1 = itemDao.displayAllEmployees();


                                    // Iterate over the items and generate <option> elements
                                    for (Item item : items) {


                                %>
                                <option value="<%= item.getItemCode() %>"><%= item.getItemName() %> - <%= item.getItemCode()%> - <%= item.getItemMeasure()%></option>
                                <%
                                    }
                                %>

                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="items" class="form-label">itemList</label>
                            <select class="form-select" name="itemList" id="items" >
                                <%
                                    // Fetch items from the database using the DAO
                                    ItemDao dao = new ItemDao();
                                    List<Item> Total = itemDao.displayAllEmployees();

                                    Long totalPrice = 0L;
                                    Long quantity = 0L;

                                    // Iterate over the items and accumulate total values
                                    for (Item item : items) {
                                        try {
                                            // Set quantity based on item measure
                                            if ("box".equals(item.getItemMeasure())) {
                                                quantity = 5L;
                                            } else {
                                                quantity = 10L;
                                            }

                                            // Calculate totalPrice using quantity
                                            totalPrice += Long.valueOf(item.getItemUnit()) * quantity;
                                        } catch (NumberFormatException e) {
                                            // Handle the exception (e.g., log or set a default value for totalPrice)
                                        }
                                %>
<%--                                <option value="<%= item.getItemCode() %>"><%= totalPrice %></option>--%>
                                <option value="<%= item.getItemCode() %>"><%= item.getItemName() %> - <%= quantity %> <%= item.getItemName() %> <%= "Total price"%> <%=totalPrice%></option>
                                <%
                                    }
                                %>


                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>

                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Add the following script block at the end of your JSP file, before the closing </body> tag -->

<script>
    // Attach an event listener to the first dropdown to trigger the update
    document.getElementById("item2").addEventListener("change", function () {
        // Get the selected item ID from the first dropdown
        var selectedItem = document.getElementById("item2");
        var selectedItemId = selectedItem.value;

        // Get the calculated total price and quantity from the stored item details
        var totalPrice = itemDetails[selectedItemId].totalPrice;
        var quantity = itemDetails[selectedItemId].quantity;
        var vat = itemDetails[selectedItemId].vat;
        var vatItem = itemDetails[selectedItemId].vatItem;


        // Update the content of the second dropdown
        var itemsDropdown = document.getElementById("items");
        itemsDropdown.innerHTML = '<option value="' + selectedItemId + '" selected>' +
            'Total Price: ' + totalPrice + ' - Quantity: ' + quantity +  ' Vat : ' + vatItem + '</option>';
    });
</script>
</body>
</html>
