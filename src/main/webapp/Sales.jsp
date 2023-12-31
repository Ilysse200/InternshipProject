<%@ page import="com.example.rraretailbusiness.dao.CustomerDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.rraretailbusiness.dao.ItemDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Item" %>
<%@ page import="com.example.rraretailbusiness.dao.EmployeeDao" %>
<%@ page import="com.example.rraretailbusiness.domain.Employee" %><%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 27/12/2023
  Time: 6:45 AM
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
                <div class="card-header text-center fs-3">Sales Record</div>
                <div class="card-body">
                    <form method="POST" action="registerSales">
                        <div class="mb-3">
                            <label for="salesdate" class="form-label">Sales Date</label>
                            <input type="date" name="salesDate" class="form-control" id="salesdate" required>
                        </div>
                        <div class="mb-3">
                            <label for="customers" class="form-label">CustomerId</label>
                            <select class="form-select" name="customerId" id="customers" required>
                                <%
                                    // Fetch customers from the database using the DAO
                                    CustomerDao customerDao = new CustomerDao();
                                    List<Customer> customers = customerDao.displayAllCustomers();

                                    // Iterate over the customers and generate <option> elements
                                    for (Customer customer : customers) {
                                %>
                                <option value="<%= customer.getCustomerId() %>"><%= customer.getCustomerName() %></option>
                                <%
                                    }
                                %>
                            </select>
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
                        </div>
                        <div>
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
                        </div>
                        <div>
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
                        <button id="addItemButton" name="addButton">Add Item</button>

                        <div class="mb-3">
                            <label for="employee" class="form-label">Employee</label>
                            <select class="form-select" name="empPurchase" id="employee" required>
                                <%
                                    // Fetch employees from the database using the DAO
                                    EmployeeDao employeeDao = new EmployeeDao();
                                    List<Employee> employees = employeeDao.displayAllEmployees();

                                    // Iterate over the employees and generate <option> elements
                                    for (Employee employee : employees) {
                                %>
                                <option value="<%= employee.getEmpID() %>"><%= employee.getEmpFirstName() %></option>
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
<%--<script>--%>
<%--    function showItemDetails() {--%>
<%--        var selectedItemCode = document.getElementById("items").value;--%>
<%--        var url = "/getItemDetails?itemCode=" + selectedItemCode;--%>

<%--        fetch(url)--%>
<%--            .then(response => response.json())--%>
<%--            .then(data => {--%>
<%--                displayItemDetails(data);--%>
<%--            })--%>
<%--            .catch(error => console.error('Error:', error));--%>
<%--    }--%>

<%--    function displayItemDetails(itemDetails) {--%>
<%--        var tableBody = document.getElementById("itemDetailsBody");--%>
<%--        tableBody.innerHTML = "";--%>

<%--        var newRow = tableBody.insertRow(0);--%>
<%--        var cell1 = newRow.insertCell(0);--%>
<%--        var cell2 = newRow.insertCell(1);--%>

<%--        cell1.innerHTML = itemDetails.itemCode;--%>
<%--        cell2.innerHTML = itemDetails.itemName;--%>

<%--        document.getElementById("itemDetailsTable").style.display = "block";--%>
<%--    }--%>
<%--</script>--%>
</body>
</html>

