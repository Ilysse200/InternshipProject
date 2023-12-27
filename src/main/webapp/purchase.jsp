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
                            <select class="form-select" name="purchaseSupplier" id="suppliers" required>
                                <%
                                    // Fetch suppliers from the database using the DAO
                                    SupplierDao supplierDao = new SupplierDao();
                                    List<Supplier> suppliers = supplierDao.displayAllSuppliers();

                                    // Iterate over the suppliers and generate <option> elements
                                    for (Supplier supplier : suppliers) {
                                %>
                                <option value="<%= supplier.getSupplierId() %>"><%= supplier.getSupplierName() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="items" class="form-label">Items</label>
                            <select class="form-select" name="items" id="items" required>
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
</body>
</html>
