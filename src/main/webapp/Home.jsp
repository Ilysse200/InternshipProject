<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 28/12/2023
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <title>Home Page</title>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="jumbotron">
        <h1 class="display-4">Welcome to Retail Business Management System!</h1>
        <p class="lead">Manage your transaction</p>
        <hr class="my-4">

        <!-- Example links to other pages -->
        <a class="btn btn-primary btn-sm" href="Supplier.jsp" role="button">Record Supplier</a>
        <a class="btn btn-success btn-sm" href="Customer.jsp" role="button">Record Customer</a>
        <a class="btn btn-primary btn-sm" href="purchase.jsp" role="button">Record Purchase</a>
        <a class="btn btn-success btn-sm" href="item.jsp" role="button">Record Item</a>
        <a class="btn btn-primary btn-sm" href="Sales.jsp" role="button">Record Sales</a>
        <a class="btn btn-success btn-sm" href="PdfReport.jsp" role="button">Generate Reports</a>
        <a class="btn btn-success btn-sm" href="SalesReport.jsp" role="button">Sales Reports</a>
        <a class="btn btn-primary btn-sm" href="result.jsp" role="button">Calculate the balance</a><br>

<%--        <button type="submit" class="btn btn-primary col-md-12" value="<a hr></a>">Logout</button>--%>

        <div class="container text-center my-5">
            <div class="jumbotron">
                <!-- Logout button with a different style and larger size -->
                <a class="btn btn-outline-danger btn-sm btn-block mt-3" href="login.jsp" role="button">Logout</a>
            </div>
        </div>





    </div>
</div>

<!-- Bootstrap JavaScript (optional) -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-J2LZN0AqDUFlKVeGf0YU5L/5UHR/OsdKtD+Bk7biGouRtF/iEIrX6PUpFJjoJ9d7" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
