<%@ page import="com.example.rraretailbusiness.dao.EmployeeDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rraretailbusiness.dao.ItemDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Item" %>
<%@ page import="com.example.rraretailbusiness.dao.SupplierDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Supplier" %><%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 27/12/2023
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Purchases</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3 mt-2">
            <div class="card">
                <div class="card-header text-center fs-3">Purchases Record</div>
                <div class="card-body">
                    <form method="POST" action="registerPurchase">
                        <div class="mb-3">
                            <label for="dateOfPurchase" class="form-label">Purchase Date</label>
                            <input type="date" name="purchaseDate" class="form-control" id="dateOfPurchase" required>
                        </div>
                        <div class="mb-3">
                            <label for="suppliers" class="form-label">Suppliers</label>
                            <input type="number" name="supplierId" class="form-control" id="suppliers" required>
                        </div>

                        <div class="mb-3">
                            <label for="itemNames" class="form-label">Items</label>
                            <select class="form-select" name="itemList" id="itemNames" required>
                                <%
                                    // Fetch items from the database using the DAO
                                    ItemDao itemDao = new ItemDao();
                                    List<Item> items = itemDao.displayAllEmployees();

                                    // Iterate over the items and generate <option> elements
                                    for (Item item : items) {
                                %>
                                <option value="<%= item.getItemCode() %>"><%= item.getItemName() %></option>
                                <%
                                    }
                                %>
                            </select>
                            <label for="itemQuantity" class="form-label">Item Quanity</label>
                            <select class="form-select" name="itemList" id="itemQuantity" required>
                                <%
                                    // Fetch items from the database using the DAO
                                    ItemDao itemDao1 = new ItemDao();
                                    List<Item> itemList = itemDao.displayAllEmployees();

                                    // Iterate over the items and generate <option> elements
                                    for (Item item : items) {
                                %>
                                <option value="<%= item.getItemCode() %>"><%= item.getItemMeasure() %></option>
                                <%
                                    }
                                %>
                            </select>
                            <label for="itemsUnitPrices" class="form-label">ItemPrices</label>
                            <select class="form-select" name="itemList" id="itemsUnitPrices" required>
                                <%
                                    // Fetch items from the database using the DAO
                                    ItemDao itemDao2 = new ItemDao();
                                    List<Item> itemList1 = itemDao.displayAllEmployees();

                                    // Iterate over the items and generate <option> elements
                                    for (Item item : itemList1) {
                                %>
                                <option value="<%= item.getItemCode() %>"><%= item.getItemUnit() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

<%--                        <div class="tab tab-1">--%>
<%--                        <table id ="table" border="1">--%>
<%--                            <tr>--%>
<%--                                <th>ItemFlow date</th>--%>
<%--                                <th>Item Name</th>--%>
<%--                                <th>ItemFlow Sale</th>--%>

<%--                            </tr>--%>
<%--                        </table>--%>
<%--                            <div class="tab">--%>
<%--                                ItemFlow date: <input type="date" name="">--%>
<%--                            </div>--%>
                        <button id="addItemButton" name="submitButton">Add Item</button>


                        <div class="mb-3">
                            <label for="employee" class="form-label">Employee</label>
                            <input type="number" name="empPurchase" class="form-control" id="employee" required>
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- ... existing code ... -->



<!-- ... existing code ... -->

<%--<script>--%>
<%--    function saveItems() {--%>
<%--        // Get selected item details--%>
<%--        var selectedItemCode = document.getElementById("itemNames").value;--%>
<%--        var selectedItemQuantity = document.getElementById("itemQuantity").value;--%>
<%--        var selectedItemUnitPrice = document.getElementById("itemsUnitPrices").value;--%>

<%--        // Create hidden input fields to store the selected item details--%>
<%--        var itemCodeInput = document.createElement("input");--%>
<%--        itemCodeInput.setAttribute("type", "hidden");--%>
<%--        itemCodeInput.setAttribute("name", "selectedItemCode");--%>
<%--        itemCodeInput.setAttribute("value", selectedItemCode);--%>

<%--        var itemQuantityInput = document.createElement("input");--%>
<%--        itemQuantityInput.setAttribute("type", "hidden");--%>
<%--        itemQuantityInput.setAttribute("name", "selectedItemsQuantity");--%>
<%--        itemQuantityInput.setAttribute("value", selectedItemQuantity);--%>

<%--        var itemUnitPriceInput = document.createElement("input");--%>
<%--        itemUnitPriceInput.setAttribute("type", "hidden");--%>
<%--        itemUnitPriceInput.setAttribute("name", "selectedItemsUnitPrice");--%>
<%--        itemUnitPriceInput.setAttribute("value", selectedItemUnitPrice);--%>

<%--        // Append the hidden input fields to the form--%>
<%--        document.forms[0].appendChild(itemCodeInput);--%>
<%--        document.forms[0].appendChild(itemQuantityInput);--%>
<%--        document.forms[0].appendChild(itemUnitPriceInput);--%>

<%--        // Optionally, you can also display a message or perform additional actions--%>

<%--        // Submit the form--%>
<%--        document.forms[0].submit();--%>
<%--    }--%>
<%--</script>--%>

<!-- ... existing code ... -->

</body>
</html>
