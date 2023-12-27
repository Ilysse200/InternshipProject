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
                            <input type="date" name="itemFlowDate" class="form-control" id="date" required>
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
                            <label for="items" class="form-label">itemList</label>
                            <select class="form-select" name="itemList" id="items" required>
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
                        <button type="submit" class="btn btn-primary col-md-12">Register</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
