<%--
  Created by IntelliJ IDEA.
  User: 25078
  Date: 15/01/2024
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h5 class="display-4">Sales Report!</h5>
        <p class="lead">Balance : </p>
        <hr class="my-4">
        <form method="post" action="salesReport">
            <div class="mb-3">
<%--                <label for="choice" class="form-label">Item Code</label>--%>
<%--                <input type="number" name="itemCode" class="form-control" id="choice" required>--%>
                <button type="submit">Balance</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
