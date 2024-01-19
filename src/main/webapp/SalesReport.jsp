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
        <h5 class="display-4">Purchases Report!</h5>
        <p class="lead">Report : </p>
        <hr class="my-4">
        <form method="post" action="purchasesReport">
            <div class="mb-3">
                <label for="choice1" class="form-label">Item Code</label>
                <input type="date" name="startDate" class="form-control" id="choice1" required>

                <label for="choice2" class="form-label">Item Code</label>
                <input type="date" name="endDate" class="form-control" id="choice2" required>

                <input type="submit" value="Generate PDF">
            </div>
        </form>
    </div>
</div>
</body>
</html>
